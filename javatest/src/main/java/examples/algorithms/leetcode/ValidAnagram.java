package examples.algorithms.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/valid-anagram/description/
 *
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Example 1: Input: s = "anagram", t = "nagaram" Output: true
 *
 * Example 2: Input: s = "rat", t = "car" Output: false
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 10^4 s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to
 * such a case?
 */
public class ValidAnagram {

  public static void main(String[] args) {
    System.out.println(isAnagram("anagram", "nagaram"));
    System.out.println(isAnagram("rat", "cat"));
    System.out.println(isAnagram("ab", "a"));
  }

  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    var map = new HashMap<Character, Integer>();
    for (int i = 0; i < s.length(); i++) {
      map.merge(s.charAt(i), 1, (oldV, v) -> oldV + v);
    }

    for (int i = 0; i < t.length(); i++) {
      final char tChar = t.charAt(i);
      Integer count = map.get(tChar);
      if (count == null || count == 0) {
        return false;
      }
      map.put(tChar, --count);
    }
    return true;
  }
}
