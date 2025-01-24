package examples.algorithms.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 *
 * You are given a string s and an integer k. You can choose any character of the string and change
 * it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after
 * performing the above operations.
 *
 * Example 1: Input: s = "ABAB", k = 2 Output: 4 Explanation: Replace the two 'A's with two 'B's or
 * vice versa.
 *
 * Example 2: Input: s = "AABABBA", k = 1 Output: 4 Explanation: Replace the one 'A' in the middle
 * with 'B' and form "AABBBBA". The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5 s consists of only uppercase English letters. 0 <= k <= s.length
 */
public class LongestRepeatingCharReplacement {


  public static void main(String[] args) {
    // Example 1
    String s1 = "ABAB";
    int k1 = 2;
    System.out.println("Output: " + characterReplacement(s1, k1)); // Output: 4

    // Example 2
    String s2 = "AABABBA";
    int k2 = 1;
    System.out.println("Output: " + characterReplacement(s2, k2)); // Output: 4

    // Example 3
    String s3 = "AABAABCBCCAACC";
    int k3 = 2;
    System.out.println("Output: " + characterReplacement(s3, k3)); // Output: 6
  }

  public static int characterReplacement(String s, int k) {
    int result = 0; // To store the maximum length of the substring
    int maxCount = 0; // To track the maximum frequency of any single character in the current window
    var countMap = new HashMap<Character, Integer>(); // HashMap to count the frequency of each character in the current window

    // Use two pointers to define the sliding window: left and right
    for (int left = 0, right = 0; right < s.length(); right++) {
      final char rightChar = s.charAt(right); // Current character at the right pointer
      var counter = countMap.getOrDefault(rightChar, 0); // Get current count of the rightChar
      countMap.put(rightChar, ++counter); // Increment the frequency of rightChar in the map
      maxCount = Math.max(maxCount, counter); // Update maxCount to reflect the highest frequency in the current window

      // This while loop reduces the window size from the left to ensure that the current sliding window is valid.
      // A window is valid if the number of characters we need to replace (window size - maxCount)
      // is less than or equal to k (the maximum replacements allowed).
      // Window size: right−left+1. This gives the total number of characters currently in the sliding window.
      while ((right - left + 1) - maxCount > k) {
        final char leftChar = s.charAt(left); // Character at the left pointer
        counter = countMap.getOrDefault(leftChar, 0); // Get current count of the leftChar
        countMap.put(leftChar, --counter); // Decrement the frequency of leftChar in the map
        left++; // Shrink the window from the left
      }

      result = Math.max(result, right - left + 1);
    }

    return result; // Return the maximum length of the valid substring
  }
}
