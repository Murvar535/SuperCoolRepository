import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Введите " + n + " элементов массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Исходный массив: " + Arrays.toString(array));

        bubbleSort(array);

        System.out.println("Отсортированный массив: " + Arrays.toString(array));

        scanner.close();
    }
    
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        boolean swapped;

        for (int i = 0; i < length - 1; i++) {
            swapped = false;

            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Обмен элементов
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}