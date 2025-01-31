package examples.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 * <p>
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, p.length <= 3 * 10^4
 * s and p consist of lowercase English letters.
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
        System.out.println(findAnagrams("abab", "ab"));        // [0, 1, 2]
    }

    /**
     * Arrays as maps with english alphabet, as counters of chars provided from strings a and p
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Populate frequency for p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int windowSize = p.length();
        for (int i = 0; i < s.length(); i++) {
            // Добавляем текущий символ в окно (увеличиваем его счетчик)
            sCount[s.charAt(i) - 'a']++;

            // Уменьшаем счетчик символа слева от текущего окна
            if (i >= windowSize) {
                sCount[s.charAt(i - windowSize) - 'a']--;
            }

            // Compare the counts
            if (areArraysEqual(pCount, sCount)) {
                // Пишем начальный символ текущего окна
                result.add(i - windowSize + 1);
            }
        }
        return result;
    }

    private static boolean areArraysEqual(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * HashMaps - slower solution. Similar to {@link PermutationInString}
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();

        // Populate frequency for p
        for (char c : p.toCharArray()) {
            pCount.put(c, pCount.getOrDefault(c, 0) + 1);
        }

        int windowSize = p.length();

        for (int i = 0; i < s.length(); i++) {
            // Add current character to the sliding window
            char currentChar = s.charAt(i);
            sCount.put(currentChar, sCount.getOrDefault(currentChar, 0) + 1);

            // Remove the character outside the window
            if (i >= windowSize) {
                char charToRemove = s.charAt(i - windowSize);
                if (sCount.get(charToRemove) == 1) {
                    sCount.remove(charToRemove);
                } else {
                    sCount.put(charToRemove, sCount.get(charToRemove) - 1);
                }
            }

            // Compare the two maps
            if (pCount.equals(sCount)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }
}
