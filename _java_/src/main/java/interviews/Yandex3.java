package interviews;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Input - массив целых чисел и целое число target. Нужно вернуть индекс начала и конца первого подмассива, сумма которого равна target.
 * Пример:
 * Input: nums = [9, -6, 5, 1, 4, -2], target = 10
 * Output: [2, 4] (5 + 1 + 4 = 10)
 */
public class Yandex3 {

    /**
     * Вычисляем текущую префиксную сумму sum (сумму элементов от начала до текущего индекса).
     * <ul>
     * Проверяем, встречалась ли сумма ранее за вычетом target (sum - target):
     * <li>
     * Если да, значит в мапе по индексу (sum - target) лежит последний индекс подмассива, который нужно отбросить, чтобы получить target.
     * Т.е. между его индексом + 1 и текущий индексом сумма будет target.
     * </li>
     * <li>
     * Если сумма sum ранее не встречалась, сохраняем её в хэш-таблице.
     * </li>
     * </ul>
     * <p>
     * Временная сложность:
     * <p>
     * O(n) – мы проходим массив всего один раз.
     * <p>
     * Пространственная сложность:
     * <p>
     * O(n) – в худшем случае храним все префиксные суммы.
     */
    public static int[] findTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        var prefixSumMap = new HashMap<Integer, Integer>();
        int sum = 0;

        // Инициализируем карту префиксных сумм значением 0 на позиции -1
        // На случай если элемент == target
        prefixSumMap.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Вычисляем префиксную сумму

            // Проверяем, был ли ранее (sum - target) в карте,
            // это значит что накопилась сумма, которая точно содержит target.
            // Находим последний элемент на котором сумма была меньше на target, со следующего начинается наш искомый помассив.
            if (prefixSumMap.containsKey(sum - target)) {
                return new int[]{prefixSumMap.get(sum - target) + 1, i};
            }

            // Добавляем текущую сумму в карту, если ее там еще нет
            prefixSumMap.putIfAbsent(sum, i);
        }

        return new int[]{-1, -1}; // Возвращаем (-1, -1), если подмассив не найден
    }

    public static void main(String[] args) {
        int[] nums = {9, -6, 5, 1, 4, -2};
        int target = 10;
        System.out.println(Arrays.toString(findTarget(nums, target))); // [2, 4]
    }
}
