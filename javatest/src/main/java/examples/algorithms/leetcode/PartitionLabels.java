package examples.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/description/
 *
 * You are given a string s. We want to partition the string into as many parts as possible so that
 * each letter appears in at most one part.
 *
 * Note that the partition is done so that after concatenating all the parts in order, the resultant
 * string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 *
 * Example 1: Input: s = "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation: The partition is
 * "ababcbaca", "defegde", "hijhklij". This is a partition so that each letter appears in at most
 * one part. A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into
 * less parts.
 *
 * Example 2: Input: s = "eccbbbbdec" Output: [10]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500 s consists of lowercase English letters.
 */
public class PartitionLabels {

  public static void main(String[] args) {
    String s = "ababcbacadefegdehijhklij";
    System.out.println("Input: " + s);
    System.out.println("Partitions: " + partitionLabels(s)); // Output: [9, 7, 8]
    System.out.println();

    s = "bobhaspepper";
    System.out.println("Input: " + s);
    System.out.println("Partitions: " + partitionLabels(s)); // Output: [3, 1, 1, 1, 5, 1]
  }

  public static List<Integer> partitionLabels(String s) {
    var lastIdxMap = new HashMap<Character, Integer>();
    var result = new LinkedList<Integer>();

    for (int i = 0; i < s.length(); i++) {
      lastIdxMap.put(s.charAt(i), i);
    }

    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
      end = Math.max(end, lastIdxMap.get(s.charAt(i))); // Expand the partition
      System.out.println("Char: " + s.charAt(i) + ", End: " + end); // Debug
      if (i == end) { // Close the partition
        result.add(end - start + 1);
        System.out.println("Partition Added: " + (end - start + 1)); // Debug
        start = i + 1; // Start new partition
      }
    }

    return result;
  }

  /**
   * Решение с leetcode.
   * s.charAt(i) - 'a' - по сути просто вычисление индекса в массиве.
   * Номер латинской буквы a в ASCII = 97, остальные буквы последовательно увеличиваются.
   */
  public static List<Integer> partitionLabels1(String s) {
    int[] last = new int[26];
    for (int i = 0; i < s.length(); ++i)
      last[s.charAt(i) - 'a'] = i;

    int end = 0, start = 0;
    List<Integer> ans = new ArrayList();

    for (int i = 0; i < s.length(); ++i) {
      end = Math.max(end, last[s.charAt(i) - 'a']);
      if (i == end) {
        ans.add(i - start + 1);
        start = i + 1;
      }
    }
    return ans;
  }
}
