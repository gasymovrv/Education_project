package examples.algorithms.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/permutation-in-string/
 * <p>
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s1.length, s2.length <= 10^4
 * s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString {

    public static void main(String[] args) {
        PermutationInString solution = new PermutationInString();

        // Test cases
        System.out.println(solution.checkInclusion("ab", "eidbaooo")); // Output: true
        System.out.println(solution.checkInclusion("ab", "eidboaoo")); // Output: false
    }

    /**
     * Моя реализация с использованием HashMap.
     * Очень похожа на FindAllAnagramsInString
     * Для оптимизации можно заменить HashMap на int[26] с английскими символами.
     * Заполнять его стандартным подходом: s1Count[s.charAt(i) - 'a']++
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        var s1Count = new HashMap<Character, Integer>();
        var s2Count = new HashMap<Character, Integer>();

        // Populate frequency for s1
        for (char c : s1.toCharArray()) {
            s1Count.merge(c, 1, Integer::sum);
        }

        for (int i = 0; i < s2.length(); i++) {
            // Добавляем текущий символ в окно (увеличиваем его счетчик)
            s2Count.merge(s2.charAt(i), 1, Integer::sum);

            // Уменьшаем счетчик символа слева от текущего окна
            if (i >= s1.length()) {
                s2Count.merge(s2.charAt(i - s1.length()), 1, (oldV, v) -> {
                    int newVal = oldV - v;
                    if (newVal == 0) return null;
                    return newVal;
                });
            }

            if (s1Count.equals(s2Count)) {
                return true;
            }
        }

        return false;
    }
}
