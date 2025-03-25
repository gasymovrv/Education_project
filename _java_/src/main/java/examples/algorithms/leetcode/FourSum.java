package examples.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/4sum/description/
 * <p>
 * Дан массив чисел - nums размером n.
 * Дано целое число target.
 * Необходимо вернуть список списков, где каждый вложенный список - это "квадруплет", т.е. список из 4х уникальных элементов из nums, сумма которых равна target.
 * Оригинальное условие:
 * <p>
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * Example 2:
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 */
public class FourSum {

    public static void main(String[] args) {
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println(fourSum(nums1, target1));

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println(fourSum(nums2, target2));
    }

    /**
     * <h3>Главная идея алгоритма:</h3>
     * <ul>
     *     <li>Сортировка массива: Упрощает работу с указателями и исключение дубликатов.</li>
     *     <li>Фиксация первых двух чисел: Два вложенных цикла фиксируют первые два элемента квадруплета.</li>
     *     <li>Использование двух указателей: Левый и правый указатели двигаются навстречу друг другу, чтобы найти два числа, которые дополняют сумму до target.</li>
     *     <li>Пропуск дубликатов: После нахождения решения игнорируются повторяющиеся элементы, чтобы избежать одинаковых квадруплетов.</li>
     * </ul>
     * <p>
     * <h3>Сложность:</h3>
     * <ul>
     *     <li>Временная сложность: O(n^3), так как сортировка O(n log n), а два вложенных цикла и указатели дают O(n^3) в худшем случае.</li>
     *     <li>Пространственная сложность: O(1), если не учитывать выходной список. Выходной список потребует O(k), где kk — количество найденных квадруплетов.</li>
     * </ul>
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // Если количество чисел меньше 4, квадруплетов быть не может
        if (nums == null || nums.length < 4) return result;

        // Сортируем массив для удобного использования двух указателей
        Arrays.sort(nums);

        // Первый цикл фиксирует первый элемент квадруплета
        for (int i = 0; i < nums.length - 3; i++) {
            // Пропускаем повторяющиеся элементы
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Второй цикл фиксирует второй элемент квадруплета
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Пропускаем повторяющиеся элементы
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Устанавливаем указатели для поиска оставшихся двух чисел
                int left = j + 1;
                int right = nums.length - 1;

                // Пока указатели не пересеклись
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; // Используем long, чтобы избежать переполнения

                    if (sum == target) {
                        // Добавляем квадруплет в результат
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Пропускаем повторяющиеся значения
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        // Сдвигаем указатели
                        left++;
                        right--;
                    } else if (sum < target) {
                        // Если сумма меньше целевой, двигаем левый указатель вправо
                        left++;
                    } else {
                        // Если сумма больше целевой, двигаем правый указатель влево
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
