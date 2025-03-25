package examples.algorithms;

/**
 * Что такое Binary Search?
 * <p>
 * Binary Search (Двоичный поиск) — это эффективный алгоритм для поиска элемента в отсортированном массиве или списке. Алгоритм работает следующим образом:
 * <p>
 * - Мы начинаем с середины массива.
 * <p>
 * - Если элемент в середине массива — это искомый элемент, то поиск завершён.
 * <p>
 * - Если искомый элемент меньше среднего элемента, то мы повторяем поиск в левой половине массива.
 * <p>
 * - Если искомый элемент больше среднего элемента, то мы ищем в правой половине массива.
 * <p>
 * - Этот процесс повторяется, пока не найдём элемент или не определим, что его нет в массиве.
 * <p>
 * Важные особенности:
 * <p>
 * Массив должен быть отсортирован.
 * Алгоритм работает за O(log N), что делает его очень быстрым для больших массивов.
 * <p>
 * <br/>
 * Пример задачи:
 * Дан массив чисел и искомое число target, найти это число в массиве и вернуть его индекс
 */
public class BinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (nums[mid] == target) {
                return mid; // Element found
            } else if (nums[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Element not found
    }

    public static void main(String[] args) {
        var arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(search(arr, 2));
        System.out.println(search(arr, 5));
        System.out.println(search(arr, 8));
        System.out.println(search(arr, 1));
        System.out.println(search(arr, 10));
    }
}

