import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TextEditor extends JFrame implements ActionListener, KeyListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;
    
    // Словарь для автозамены
    private static final Map<String, String> CORRECTIONS = new HashMap<>();
    static {
        CORRECTIONS.put("превед", "привет");
        CORRECTIONS.put("приветсвие", "приветствие");
        CORRECTIONS.put("исчо", "ещё");
        CORRECTIONS.put("программист", "программист"); // корректно
        CORRECTIONS.put("прога", "программа");
        CORRECTIONS.put("коддинг", "кодинг");
        CORRECTIONS.put("текстовая", "текстовый");
        // Добавьте больше замен по необходимости
    }
    
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> checkTask;
    private final Object lock = new Object();
    private String lastCheckedText = "";
    private long lastSpaceTime = 0;
    private static final long CHECK_DELAY_MS = 500; // Задержка проверки

    public TextEditor() {
        initUI();
        initAutoCorrect();
    }

    private void initUI() {
        setTitle("Текстовый редактор с автозаменой");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        // Текстовая область
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addKeyListener(this);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Меню
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        
        JMenuItem openItem = new JMenuItem("Открыть...");
        openItem.setActionCommand("open");
        openItem.addActionListener(this);
        
        JMenuItem saveItem = new JMenuItem("Сохранить");
        saveItem.setActionCommand("save");
        saveItem.addActionListener(this);
        
        JMenuItem saveAsItem = new JMenuItem("Сохранить как...");
        saveAsItem.setActionCommand("saveAs");
        saveAsItem.addActionListener(this);
        
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.setActionCommand("exit");
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // FileChooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Текстовые файлы (*.txt)", "txt"));

        setVisible(true);
    }
    
    private void initAutoCorrect() {
        // Запуск проверки по таймеру после пробела
        checkTask = scheduler.scheduleAtFixedRate(() -> {
            try {
                SwingUtilities.invokeLater(this::performAutoCorrect);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, CHECK_DELAY_MS, CHECK_DELAY_MS, TimeUnit.MILLISECONDS);
    }
    
    private void performAutoCorrect() {
        synchronized (lock) {
            String currentText = textArea.getText();
            
            // Проверяем только если текст изменился и прошло время после пробела
            if (!currentText.equals(lastCheckedText) && 
                (System.currentTimeMillis() - lastSpaceTime > CHECK_DELAY_MS)) {
                
                String correctedText = correctText(currentText);
                if (!correctedText.equals(currentText)) {
                    textArea.setText(correctedText);
                    textArea.setCaretPosition(textArea.getText().length());
                    JOptionPane.showMessageDialog(
                        this, 
                        "Автозамена выполнена!", 
                        "Автокоррекция", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
                lastCheckedText = correctedText;
            }
        }
    }
    
    private String correctText(String text) {
        StringBuilder corrected = new StringBuilder();
        String[] words = text.split("\\s+");
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i].replaceAll("[^а-яёА-ЯЁa-zA-Z]", "");
            
            if (CORRECTIONS.containsKey(word.toLowerCase())) {
                String correction = CORRECTIONS.get(word.toLowerCase());
                words[i] = words[i].replaceFirst(word, correction);
                
                // Подсвечиваем замену в консоли (для отладки)
                System.out.println("Заменено: '" + word + "' → '" + correction + "'");
            }
            
            corrected.append(words[i]);
            if (i < words.length - 1) {
                corrected.append(" ");
            }
        }
        
        // Восстанавливаем знаки препинания и переносы строк
        return corrected.toString().trim();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            synchronized (lock) {
                lastSpaceTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "open" -> openFile();
            case "save" -> saveFile();
            case "saveAs" -> saveFileAs();
            case "exit" -> {
                scheduler.shutdown();
                System.exit(0);
            }
        }
    }

    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.read(reader, null);
                synchronized (lock) {
                    lastCheckedText = textArea.getText();
                }
                setTitle("Текстовый редактор - " + currentFile.getName());
                JOptionPane.showMessageDialog(this, "Файл открыт успешно!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка открытия: " + ex.getMessage(), 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            textArea.write(writer);
            JOptionPane.showMessageDialog(this, "Файл сохранен!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения: " + ex.getMessage(), 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFileAs() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            if (!currentFile.getName().endsWith(".txt")) {
                currentFile = new File(currentFile.getAbsolutePath() + ".txt");
            }
            saveFile();
            setTitle("Текстовый редактор - " + currentFile.getName());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (checkTask != null && !checkTask.isCancelled()) {
            checkTask.cancel(true);
        }
        scheduler.shutdown();
        super.finalize();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor());
    }
}
