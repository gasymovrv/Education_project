package examples.algorithms.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-sorted-array/description
 *
 * Даны 2 числовых массива nums1, nums2. Отсортированы в НЕ-убывающем порядке.
 * Также даны 2 integer числа - m, n, представляющие число элементов в массивах nums1, nums2 соответственно.
 * Однако фактически nums1 имеет длину m+n, где m значимые элементы, а n - нули.
 * Необходимо смержить массивы в один не убывающий массив, не создавать новый, а использовать свободные ячейки в nums1
 */
public class MergeSortedArrays {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Указатели для обхода массивов
        int p1 = m - 1; // Указатель на последний элемент nums1 (из значимых)
        int p2 = n - 1; // Указатель на последний элемент nums2
        int p = m + n - 1; // Указатель на место, куда записывать следующий элемент

        // Пока есть элементы в nums2
        while (p2 >= 0) {
            // Если nums1 еще не полностью обработан и его текущий элемент больше nums2
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1]; // Копируем больший элемент из nums1
                p1--; // Сдвигаем указатель влево
            } else {
                nums1[p] = nums2[p2]; // Копируем текущий элемент из nums2
                p2--; // Сдвигаем указатель влево
            }
            p--; // Сдвигаем указатель для записи влево
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0}; // Пример массива с m=3, n=3
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1)); // Ожидаемый результат: [1, 2, 2, 3, 5, 6]
    }
}
