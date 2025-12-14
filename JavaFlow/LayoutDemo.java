import javax.swing.*;
import java.awt.*;

public class LayoutDemo extends JFrame {
    public LayoutDemo() {
        setTitle("BorderLayout + FlowLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Внешний BorderLayout для JFrame
        setLayout(new BorderLayout());
        
        // Верхняя панель с кнопками (FlowLayout)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(new JButton("Новая запись"));
        topPanel.add(new JButton("Изменить"));
        topPanel.add(new JButton("Удалить"));
        topPanel.add(new JButton("Сохранить"));
        add(topPanel, BorderLayout.NORTH);
        
        // Центральная панель с текстовыми полями (FlowLayout)
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(new JLabel("Имя:"));
        centerPanel.add(new JTextField(15));
        centerPanel.add(new JLabel("Email:"));
        centerPanel.add(new JTextField(20));
        centerPanel.add(new JButton("Поиск"));
        add(centerPanel, BorderLayout.CENTER);
        
        // Нижняя панель статуса
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(new JLabel("Готов к работе"));
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayoutDemo().setVisible(true));
    }
}
