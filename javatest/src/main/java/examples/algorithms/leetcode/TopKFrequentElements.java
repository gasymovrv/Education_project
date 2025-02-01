package examples.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Дан массив чисел, найти k самых часто встречающихся чисел в виде массива. Результирующий массив может быть в любом порядке
 */
public class TopKFrequentElements {

    /**
     * Возвращает массив из k наиболее часто встречающихся элементов.
     * <p>
     * <p>
     * Временная сложность: O(N + N log N + k log N) ≈ O(N log N)
     * <p>
     * - Подсчёт частоты: O(N)
     * <p>
     * - Добавление в кучу: O(N log N)
     * <p>
     * - Извлечение k элементов: O(k log N)
     * <p>
     * <p>
     * Пространственная сложность: O(N)
     * <p>
     * - Хранение частот в HashMap: O(N)
     * <p>
     * - Хранение всех элементов в max-heap: O(N)
     *
     * @param nums Входной массив чисел
     * @param k    Количество наиболее частых элементов для возврата
     * @return Массив из k наиболее частых элементов
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // Шаг 1: Подсчёт частоты каждого числа
        var frequencyMap = new HashMap<Integer, Integer>();
        for (int a : nums) {
            frequencyMap.merge(a, 1, Integer::sum);
        }

        // Шаг 2: Использование кучи (max-heap) для сортировки элементов по частоте
        var maxHeap = new PriorityQueue<Pair<Integer, Integer>>((a, b) -> b.value() - a.value());
        for (var entry : frequencyMap.entrySet()) {
            maxHeap.offer(Pair.of(entry.getKey(), entry.getValue()));
        }

        // Шаг 3: Берем k первых элементов из кучи
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            var pair = maxHeap.poll();
            if (pair != null) {
                result[i] = pair.key();
            }
        }
        return result;
    }

    record Pair<K, V>(K key, V value) {
        public static <K, V> Pair<K, V> of(K key, V value) {
            return new Pair<>(key, value);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 6, 1, 1, 2, 7, 2, 3, 4, 5}, 2)));
    }
}
