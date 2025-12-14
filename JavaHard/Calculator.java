import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите начальную сумму (P): ");
        double principal = scanner.nextDouble();
        
        System.out.print("Введите итоговую сумму (A) или ставку (%): ");
        String input = scanner.next();
        
        System.out.print("Введите количество периодов (n): ");
        int periods = scanner.nextInt();
        
        if (input.contains("%")) {
            // Прямое вычисление
            double rate = Double.parseDouble(input.replace("%", "")) / 100.0;
            double amount = calculateCompound(principal, rate, periods);
            System.out.printf("Итоговая сумма: %.2f%n", amount);
        } else {
            // Обратное вычисление ставки
            double targetAmount = Double.parseDouble(input);
            double rate = calculateRequiredRate(principal, targetAmount, periods);
            System.out.printf("Необходимая ставка: %.2f%%%n", rate * 100);
        }
        
        scanner.close();
    }
    
    public static double calculateCompound(double principal, double rate, int periods) {
        return principal * Math.pow(1 + rate, periods);
    }
    
    public static double calculateRequiredRate(double principal, double target, int periods) {
        if (periods == 0) return 0.0;
        return Math.pow(target / principal, 1.0 / periods) - 1;
    }
}
