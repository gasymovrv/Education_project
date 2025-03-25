package interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * Даны 2 массива целых чисел одинаковой длины.
 * Проходясь одновременно по обоим массивам начиная с i == 0 нужно подсчитать,
 * сколько чисел из этих массивов (nums1[i], nums2[i]) уже встретились на индексах < i.
 * В результате вывести массив с элементами - количеством встреченных ранее чисел на каждом индексе
 */
public class Yandex5 {

    public static int[] solution(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, Integer> unpaired = new HashMap<>();
        int[] res = new int[n];
        int counter = 0;

        for (int i = 0; i < n; i++) {
            // Обработка nums1[i]
            int num1 = nums1[i];
            if (unpaired.getOrDefault(num1, 0) > 0) {
                unpaired.put(num1, unpaired.get(num1) - 1);
                counter++;
            } else {
                unpaired.put(num1, unpaired.getOrDefault(num1, 0) + 1);
            }

            // Обработка nums2[i]
            int num2 = nums2[i];
            if (unpaired.getOrDefault(num2, 0) > 0) {
                unpaired.put(num2, unpaired.get(num2) - 1);
                counter++;
            } else {
                unpaired.put(num2, unpaired.getOrDefault(num2, 0) + 1);
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
        // Ожидаемый вывод: 0, 1, 2, 4

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
