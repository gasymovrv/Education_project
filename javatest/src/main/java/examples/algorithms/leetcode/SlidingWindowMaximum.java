package examples.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * You are given an array of integers nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers in the window. Each
 * time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3 Output: [3,3,5,5,6,7]
 *
 * Example 2:
 * Input: nums = [1], k = 1 Output: [1]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // expected [3,3,5,5,6,7]
  }

  /**
   * Мое второе решение через запись индексов в очередях - O(n)
   */
  public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices of array elements

        for (int i = 0; i < n; i++) {
            // Remove indices that are out of the window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove indices of elements smaller than current element
            // as they will never be the maximum in this or any future window
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current element's index
            deque.offerLast(i);

            // Add the maximum for the current window to the result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

  /**
   * Мое первое решение - O(n * k)
   */
  public static int[] maxSlidingWindow1(int[] nums, int k) {
    int totalWindows = nums.length - k + 1;
    var maxsOfWindows = new int[totalWindows];

    int windowCounter = 0;

    for (int startWindow = 0; startWindow < nums.length; startWindow++) {
      int currentMax = nums[startWindow];

      if (startWindow + k <= nums.length) {
        for (int j = startWindow; j <= startWindow + k - 1; j++) {
          currentMax = Math.max(currentMax, nums[j]);
        }
        maxsOfWindows[windowCounter++] = currentMax;
      } else {
        break;
      }
    }

    return maxsOfWindows;
  }
}
