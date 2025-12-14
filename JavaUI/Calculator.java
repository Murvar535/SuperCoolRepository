import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {
    private JTextField display;
    private double value1, value2;
    private String operator;
    private boolean startNewNumber;

    public Calculator() {
        initUI();
    }

    private void initUI() {
        setTitle("Калькулятор");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(300, 400);

        // Дисплей
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Панель кнопок
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "C", "±", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", ""
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new CalcListener());
            buttonPanel.add(button);
            
            if (text.equals("=")) {
                button.setBackground(new Color(0, 150, 0));
            } else if ("C±%÷×".contains(text)) {
                button.setBackground(new Color(255, 140, 0));
            } else {
                button.setBackground(new Color(220, 220, 220));
            }
        }

        // Кнопка 0 шире
        buttonPanel.getComponent(16).setLayout(new GridLayout());
        ((JButton) buttonPanel.getComponent(16)).setText("0");

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private class CalcListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789.".contains(command)) {
                numberPressed(command);
            } else if ("+-×÷".contains(command)) {
                operatorPressed(command);
            } else if (command.equals("=")) {
                equalsPressed();
            } else if (command.equals("C")) {
                clear();
            } else if (command.equals("±")) {
                negate();
            } else if (command.equals("%")) {
                percent();
            }
        }
    }

    private void numberPressed(String digit) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText("");
        }
        display.setText(display.getText() + digit);
        startNewNumber = false;
    }

    private void operatorPressed(String op) {
        value1 = Double.parseDouble(display.getText());
        operator = op;
        startNewNumber = true;
    }

    private void equalsPressed() {
        value2 = Double.parseDouble(display.getText());
        double result = switch (operator) {
            case "+" -> value1 + value2;
            case "-" -> value1 - value2;
            case "×" -> value1 * value2;
            case "÷" -> value1 / value2;
            default -> value2;
        };
        display.setText(String.format("%.2f", result));
        startNewNumber = true;
    }

    private void clear() {
        display.setText("0");
        value1 = 0;
        operator = null;
        startNewNumber = true;
    }

    private void negate() {
        double value = Double.parseDouble(display.getText());
        display.setText(String.format("%.2f", -value));
    }

    private void percent() {
        double value = Double.parseDouble(display.getText());
        display.setText(String.format("%.2f", value / 100));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
