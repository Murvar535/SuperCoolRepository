import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;

    public TextEditor() {
        initUI();
    }

    private void initUI() {
        setTitle("Текстовый редактор");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        // Текстовая область
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "open" -> openFile();
            case "save" -> saveFile();
            case "saveAs" -> saveFileAs();
            case "exit" -> System.exit(0);
        }
    }

    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.read(reader, null);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextEditor());
    }
}
