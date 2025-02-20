package interviews;

import java.util.HashSet;

/**
 * Даны 2 массива целых чисел одинаковой длины.
 * Нужно считать на каждом i-ом элементе сколько элементов из второго массива уже встречалось ранее
 * и в результате вывести массив с элементами - количеством встреченных ранее чисел на каждом индексе
 */
public class Yandex5 {

    public static int[] solution(int[] nums1, int[] nums2) {
        int n = nums1.length;
        var set1 = new HashSet<Integer>();
        var set2 = new HashSet<Integer>();
        int counter = 0;

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            if (set2.contains(nums1[i])) {
                set2.remove(nums1[i]);
                counter++;
            } else {
                set1.add(nums1[i]);
            }

            if (set1.contains(nums2[i])) {
                set1.remove(nums2[i]);
                counter++;
            } else {
                set2.add(nums2[i]);
            }

            res[i] = counter;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 3};
        int[] arr2 = {2, 1, 3, 1};
        int[] result = solution(arr1, arr2);

        for (int count : result) {
            System.out.print(count + " ");
        }
        // Ожидаемый вывод: 0, 1, 2, 3

        System.out.println();

        arr1 = new int[]{1, 20, 30, 1, 40, 1};
        arr2 = new int[]{2, 1, 3, 4, 1, 5};
        result = solution(arr1, arr2);

        for (int count : result) {
            System.out.print(count + " ");
        }
        // Ожидаемый вывод: 0, 1, 1, 1, 2, 2

        System.out.println();

        arr1 = new int[]{1};
        arr2 = new int[]{1};
        result = solution(arr1, arr2);

        for (int count : result) {
            System.out.print(count + " ");
        }
        // Ожидаемый вывод: 1

        System.out.println();

        arr1 = new int[]{1, 3, 5};
        arr2 = new int[]{2, 4, 6};
        result = solution(arr1, arr2);

        for (int count : result) {
            System.out.print(count + " ");
        }
        // Ожидаемый вывод: 0, 0, 0

        System.out.println();

        arr1 = new int[]{1, 3, 5, 7};
        arr2 = new int[]{2, 4, 6, 3};
        result = solution(arr1, arr2);

        for (int count : result) {
            System.out.print(count + " ");
        }
        // Ожидаемый вывод: 0, 0, 0, 1
    }
}
