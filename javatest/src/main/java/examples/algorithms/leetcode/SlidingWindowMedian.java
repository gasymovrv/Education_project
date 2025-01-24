package examples.algorithms.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/sliding-window-median/description/
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there
 * is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3. For examples, if arr = [1,2,3,4], the median is
 * (2 + 3) / 2 = 2.5. You are given an integer array nums and an integer k. There is a sliding
 * window of size k which is moving from the very left of the array to the very right. You can only
 * see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10^-5 of the actual
 * value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3 Output:
 * [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3 Output:
 * [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5 -2^31 <= nums[i] <= 2^31 - 1
 */
public class SlidingWindowMedian {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(medianSlidingWindow1(new int[]{2147483647, 2147483647}, 2))); // expected [2147483647.00000]
    System.out.println(Arrays.toString(medianSlidingWindow1(new int[]{1, 3, -1, -3, 5, 3, 6, 7},
        3))); // expected [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
  }

  /**
   * Решение с leetcode
   */
  public static double[] medianSlidingWindow1(int[] nums, int k) {
    double[] result = new double[nums.length - k + 1];
    int start = 0;

    // Left side (lo) as a max-heap, using reverse order comparator
    TreeSet<Integer> lo = new TreeSet<>(
        (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b);

    // Right side (hi) as a min-heap, using natural order comparator
    TreeSet<Integer> hi = new TreeSet<>(
        (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b);

    // Sliding window logic
    for (int i = 0; i < nums.length; i++) {
      lo.add(i);                 // Add the new index to the 'lo' set
      hi.add(lo.pollLast());      // Move the largest of 'lo' to 'hi'
      if (hi.size() > lo.size()) {
        lo.add(hi.pollFirst()); // Rebalance if needed
      }

      // When the window size is `k`, calculate the median
      if (lo.size() + hi.size() == k) {
        // Compute median based on the size of the two heaps
        result[start] = lo.size() == hi.size()
            ? (nums[lo.last()] / 2.0 + nums[hi.first()] / 2.0)
            : nums[lo.last()];

        // Remove the element that is sliding out of the window
        if (!lo.remove(start)) {
          hi.remove(start);
        }

        start++; // Move the window forward
      }
    }

    return result;
  }


  /**
   * Мое первое решение - O(n * k)
   */
  public static double[] medianSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    var result = new double[n - k + 1];

    int windowCounter = 0;
    int[] currentWindow = new int[k];

    for (int startWindow = 0; startWindow < n; startWindow++) {

      if (startWindow + k <= n) {
        System.arraycopy(nums, startWindow, currentWindow, 0, k);
        Arrays.sort(currentWindow);

        if (k % 2 == 0) {
          double median = ((double) currentWindow[k / 2 - 1] + (double) currentWindow[k / 2]) / 2.0;
          result[windowCounter++] = median;
        } else {
          result[windowCounter++] = currentWindow[k / 2];
        }
      } else {
        break;
      }
    }
    return result;
  }
}
