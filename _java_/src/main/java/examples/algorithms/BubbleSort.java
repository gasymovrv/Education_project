package examples.algorithms;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        String[] strings = new String[]{"b", "g", "a", "c", "f", "d", "abc", "adf"};
        sort(strings);
        System.out.println(Arrays.toString(strings));

        Integer[] ints = new Integer[]{4, 2, 3, 1, 8, 6, 5, 7};
        sort(ints);
        System.out.println(Arrays.toString(ints));

        //такое не прокатит, т.к. 4.compareTo("8") бросит ClassCastException
        Comparable[] objects = new Comparable[]{4, 2, "3", 1, "8", 6, 5, 7};
        sort(objects);
        System.out.println(Arrays.toString(objects));
    }

    /**
     * Сортировка пузырьком
     */
    private static <T extends Comparable> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[i].compareTo(array[j]) > 0) {
                    T temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }
}
