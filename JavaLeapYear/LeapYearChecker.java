import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите год (положительное целое число): ");
            int year = scanner.nextInt();

            if (year <= 0) {
                throw new IllegalArgumentException("Год должен быть положительным целым числом больше 0.");
            }

            boolean isLeap = isLeapYear(year);
            System.out.printf("Год %d %sвисокосный.\n", year, isLeap ? "" : "не ");

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введено не целое число. Пожалуйста, введите год в виде целого числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static boolean isLeapYear(int year) {

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}