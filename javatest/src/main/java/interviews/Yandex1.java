package interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * Дан массив целых чисел a_1, a_2, ..., a_n и неотрицательное число k.
 * Найдите количество пар элементов массива (i, j), таких, что i < j и |a_i - a_j| = k (модуль разности равен k).
 * n < 10^5
 * -10^9 < a < 10^9
 */
public class Yandex1 {

    /**
     * Основная идея алгоритма:
     * <p>
     * Используем хеш-таблицу (Map) для хранения количества вхождений элементов массива.
     * <p>
     * Это позволяет нам за постоянное время (O(1)) проверять, существует ли в массиве элемент, который в сумме или разности с текущим элементом дает k.
     * <p>
     * Для каждого элемента массива проверяем два возможных условия:
     * <p>
     *     <ul>
     *         <li>a_i - k (проверяем, есть ли в массиве элемент, который меньше текущего на k).</li>
     *         <li>a_i + k (проверяем, есть ли в массиве элемент, который больше текущего на k).</li>
     *     </ul>
     * <p>
     * Если такие элементы есть, то это значит, что мы нашли пару, удовлетворяющую условию задачи.
     *
     * @param arr исходный массив
     * @param k   модуль разности
     * @return количество пар
     */
    public static int countPairsWithDifference(int[] arr, int k) {
        if (k < 0) return 0; // Отрицательное k невозможно по условию задачи

        Map<Integer, Integer> countMap = new HashMap<>();
        int pairCount = 0;

        for (int num : arr) {
            // Проверяем (num - k), но сначала убеждаемся, что не выходим за пределы int,
            // приводить к long нет смысла т.к. нам надо просто найти другое большее значение, а этот минимум можем скипнуть
            if (num >= Integer.MIN_VALUE + k && countMap.containsKey(num - k)) {
                pairCount += countMap.get(num - k);
            }

            // Проверяем (num + k), но сначала убеждаемся, что не выходим за пределы int,
            // приводить к long нет смысла т.к. нам надо просто найти другое меньшее значение, а этот максимум можем скипнуть.
            // Проверить на k != 0 нужно, т.к. иначе будем дважды инкрементить счетчик, например для arr=[1,1], что неверно
            if (num <= Integer.MAX_VALUE - k && k != 0 && countMap.containsKey(num + k)) {
                pairCount += countMap.get(num + k);
            }

            // Добавляем число в хеш-таблицу
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        return pairCount;
    }

    public static void main(String[] args) {
        int[] a = {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MIN_VALUE, Integer.MIN_VALUE + 1};
        int k = 1;
        System.out.println(countPairsWithDifference(a, k)); // Должно корректно обработать граничные случаи

        a = new int[]{1, 5, 3, 4, 2};
        k = 2;
        System.out.println(countPairsWithDifference(a, k)); // Ожидаем 3

        k = 0;
        System.out.println(countPairsWithDifference(a, k)); // Ожидаем 0

        a = new int[]{1, 1};
        k = 0;
        System.out.println(countPairsWithDifference(a, k)); // Ожидаем 1
    }
}
