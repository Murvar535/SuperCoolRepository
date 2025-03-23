import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortComparison {

    public static void main(String[] args) {
        // Тестирование StalinSort на маленьком массиве
        int[] smallArray = generateRandomArray(5);
        System.out.println("Маленький массив до StalinSort: " + Arrays.toString(smallArray));
        long start = System.nanoTime();
        int[] stalinSorted = stalinSort(smallArray);
        long end = System.nanoTime();
        System.out.println("После StalinSort: " + Arrays.toString(stalinSorted));
        System.out.printf("Время StalinSort (маленький массив): %.3f мс%n", (end - start) / 1e6);

        // Тестирование StalinSort на крупном массиве
        int[] largeArray = generateRandomArray(10_000);
        start = System.nanoTime();
        stalinSorted = stalinSort(largeArray);
        end = System.nanoTime();
        System.out.printf("Время StalinSort (крупный массив): %.3f мс%n", (end - start) / 1e6);

        // Тестирование BogoSort на маленьком массиве (на большом не надо))
        int[] bogoArray = generateRandomArray(10);
        System.out.println("Массив до BogoSort: " + Arrays.toString(bogoArray));
        start = System.nanoTime();
        bogoSort(bogoArray);
        end = System.nanoTime();
        System.out.println("После BogoSort: " + Arrays.toString(bogoArray));
        System.out.printf("Время BogoSort: %.3f мс%n", (end - start) / 1e6);
    }

    // StalinSort: удаляет элементы, которые не отсортированы
    public static int[] stalinSort(int[] array) {
        if (array.length == 0) return new int[0];
        List<Integer> sorted = new ArrayList<>();
        int last = array[0];
        sorted.add(last);
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= last) {
                sorted.add(array[i]);
                last = array[i];
            }
        }
        return sorted.stream().mapToInt(i -> i).toArray();
    }

    // BogoSort: случайно перемешивает массив до тех пор, пока он не отсортируется
    public static void bogoSort(int[] array) {
        Random rnd = new Random();
        while (!isSorted(array)) {
            shuffle(array, rnd);
        }
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void shuffle(int[] array, Random rnd) {
        for (int i = array.length; i > 1; i--) {
            int j = rnd.nextInt(i);
            int temp = array[i - 1];
            array[i - 1] = array[j];
            array[j] = temp;
        }
    }

    private static int[] generateRandomArray(int size) {
        Random rnd = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rnd.nextInt(100);
        }
        return array;
    }
}
