package examples.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/group-anagrams/
 * <p>
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Explanation:
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

    /**
     * Для группировки анаграмм мы можем использовать хэш-таблицу,
     * где ключом будет отсортированное содержимое строки (которое одинаково для всех анаграмм),
     * а значением — список строк, принадлежащих одной группе анаграмм.
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        var map = new HashMap<String, List<String>>();
        for (var str : strs) {
            // Можно использовать сортировку
            // Это чуть медленнее чем вариант ниже, т.к. сортировка нам даст O(n log n), но при этом чуть лучше по памяти.
            var sortedStr = str.toCharArray();
            Arrays.sort(sortedStr);

            // Вместо сортировки можно использовать упорядоченный массив чаров с английскими символами
            // Тут будет O(n), но при этом добавит использование памяти, т.к. создаем новые подмассивы
            //char[] sortedStr = new char[26];
            //for (int i = 0; i < str.length(); i++) {
            //    sortedStr[str.charAt(i) - 'a'] += 1;
            //}

            var key = String.valueOf(sortedStr);
            var anagrams = map.get(key);

            if (anagrams == null) {
                var list = new ArrayList<String>();
                list.add(str);
                map.put(key, list);
            } else {
                anagrams.add(str);
            }
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        // Пример использования
        String[] strList = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strList);

        // Вывод результата
        System.out.println(result);
    }
}
