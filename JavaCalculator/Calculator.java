import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение: ");
        String input = scanner.nextLine();
        try {
            double result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static double calculate(String input) {
        input = input.replaceAll("\\s+", "");
        
        String[] parts = input.split("[+\\-*/]");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        char operator = 0;
        for (char c : input.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operator = c;
                break;
            }
        }

        if (operator == 0) {
            throw new IllegalArgumentException("Оператор не найден");
        }

        try {
            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[1]);

            switch (operator) {
                case '+':
                    return num1 + num2;
                case '-':
                    return num1 - num2;
                case '*':
                    return num1 * num2;
                case '/':
                    if (num2 == 0) {
                        throw new IllegalArgumentException("Деление на ноль");
                    }
                    return num1 / num2;
                default:
                    throw new IllegalArgumentException("Недопустимый оператор");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат чисел");
        }
    }
}