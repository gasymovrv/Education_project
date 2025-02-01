package examples.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Дан массив строк (слов), найти k самых часто встречающихся слов в виде списка.
 * Результирующий список должен быть отсортирован в порядке убывания частоты встречаемости слов и если частота одинакова то лексикографически.
 */
public class TopKFrequentWords {

    /**
     * Логика алгоритма такая же как в {@link TopKFrequentElements}, но есть дополнительная лексикографическая сортировка слов в куче.
     * <p>
     * Сложность такая же как в {@link TopKFrequentElements}
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // Шаг 1: Подсчёт частоты каждого числа
        var frequencyMap = new HashMap<String, Integer>();
        for (String s : words) {
            frequencyMap.merge(s, 1, Integer::sum);
        }

        // Шаг 2: Использование кучи (max-heap) для сортировки слов по частоте,
        // если частота одинакова, то сортировка слов лексикографически
        var maxHeap = new PriorityQueue<Pair<String, Integer>>((a, b) -> {
            var freqCompareResult = (b.value() - a.value());
            if (freqCompareResult == 0) {
                return a.key().toLowerCase().compareTo(b.key().toLowerCase());
            }
            return freqCompareResult;
        });
        for (var entry : frequencyMap.entrySet()) {
            maxHeap.offer(Pair.of(entry.getKey(), entry.getValue()));
        }

        // Шаг 3: Берем k первых слов из кучи
        var result = new ArrayList<String>(k);
        for (int i = 0; i < k; i++) {
            var pair = maxHeap.poll();
            if (pair != null) {
                result.add(pair.key());
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
        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }
}
