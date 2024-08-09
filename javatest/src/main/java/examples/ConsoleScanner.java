package examples;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleScanner {

    public static void main(String[] args) {
        int[] ints1 = readNumbers(10);
        int[] ints2 = Arrays.copyOf(ints1, ints1.length);
        int[] ints3 = Arrays.copyOf(ints1, ints1.length);
        int[] ints4 = Arrays.copyOf(ints1, ints1.length);

        printArray(ints1);
        withTimeTracker(ConsoleScanner::doCustomSort, ints1);
        printArray(ints1);

        System.out.println();
        printArray(ints2);
        withTimeTracker(ConsoleScanner::doBubbleSort, ints2);
        printArray(ints2);

        System.out.println();
        printArray(ints3);
        withTimeTracker(ConsoleScanner::doSelectionSort, ints3);
        printArray(ints3);

        System.out.println();
        printArray(ints4);
        withTimeTracker(ConsoleScanner::doInsertingSort, ints4);
        printArray(ints4);
    }

    private static int[] readNumbers(int numbersCount) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = new int[numbersCount];

        for (int i = 0; i < numbersCount; i++) {
            ints[i] = scanner.nextInt();
        }

        return ints;
    }

    private static void doCustomSort(int[] array) {
        System.out.println("Сортировка самодельная");
        int iterationsCount = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
                iterationsCount++;
            }
        }

        System.out.println("iterationsCount=" + iterationsCount);
    }

    private static void doBubbleSort(int[] array) {
        System.out.println("Сортировка пузырьком");
        int iterationsCount = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
                iterationsCount++;
            }
        }

        System.out.println("iterationsCount=" + iterationsCount);
    }

    private static void doSelectionSort(int[] array) {
        System.out.println("Сортировка выборками");
        int iterationsCount = 0;

        for (int barier = 0; barier < array.length - 1; barier++) {
            for (int i = barier + 1; i < array.length; i++) {
                if (array[i] < array[barier]) {
                    int tmp = array[i];
                    array[i] = array[barier];
                    array[barier] = tmp;
                }
                iterationsCount++;
            }
        }

        System.out.println("iterationsCount=" + iterationsCount);
    }

    private static void doInsertingSort(int[] array) {
        System.out.println("Сортировка вставками");
        int iterationsCount = 0;

        for (int barier = 1; barier < array.length; barier++) {
            int index = barier;
            while (index - 1 >= 0 && array[index] < array[index - 1]) {
                int tmp = array[index];
                array[index] = array[index - 1];
                array[index - 1] = tmp;
                index--;
                iterationsCount++;
            }
        }

        System.out.println("iterationsCount=" + iterationsCount);
    }

    private static void withTimeTracker(Consumer<int[]> consumer, int[] array) {
        long l1 = System.nanoTime();
        consumer.accept(array);
        long l2 = System.nanoTime();
        System.out.println("time, nanos =" + (l2 - l1));
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
