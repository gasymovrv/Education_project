package examples.algorithms;

import java.util.Arrays;

public class BubbleSortingTest {
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
    private static <T extends Comparable> void sort(T[] n) {
        for (int i = 0; i < n.length; i++) {
            for (int j = n.length - 1; j > i; j--) {
                if (n[i].compareTo(n[j]) > 0) {
                    T temp = n[j];
                    n[j] = n[i];
                    n[i] = temp;
                }
            }
        }
    }
}
