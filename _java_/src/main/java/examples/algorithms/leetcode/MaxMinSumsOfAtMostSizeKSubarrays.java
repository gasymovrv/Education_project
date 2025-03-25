package examples.algorithms.leetcode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays/description/
 * <p>
 * You are given an integer array nums and a positive integer k. Return the sum of the maximum and
 * minimum elements of all subarrays with at most k elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], k = 2
 * <p>
 * Output: 20
 * <p>
 * Explanation:
 * <p>
 * The subarrays of nums with at most 2 elements are:
 * <p>
 * Subarray	Minimum	Maximum	Sum [1]	1	1	2 [2]	2	2	4 [3]	3	3	6 [1, 2]	1	2	3 [2, 3]	2	3	5 Final
 * Total
 * 20 The output would be 20.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,-3,1], k = 2
 * <p>
 * Output: -6
 * <p>
 * Explanation:
 * <p>
 * The subarrays of nums with at most 2 elements are:
 * <p>
 * Subarray	Minimum	Maximum	Sum [1]	1	1	2 [-3]	-3	-3	-6 [1]	1	1	2 [1, -3]	-3	1	-2 [-3, 1]
 * -3	1	-2
 * Final Total	 	 	-6 The output would be -6.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 80000 1 <= k <= nums.length -10^6 <= nums[i] <= 10^6
 */
public class MaxMinSumsOfAtMostSizeKSubarrays {

    public static void main(String[] args) {
        System.out.println(minMaxSubarraySum1(new int[]{1, 2, 3}, 2));
        System.out.println(minMaxSubarraySum1(new int[]{1, -3, 1}, 2));
        System.out.println();
        System.out.println(minMaxSubarraySum2(new int[]{1, 2, 3}, 2));
        System.out.println(minMaxSubarraySum2(new int[]{1, -3, 1}, 2));
    }


    /**
     * My solution (slow)
     */
    public static long minMaxSubarraySum1(int[] nums, int k) {
        long result = 0;
        int n = nums.length;

        // Traverse each possible subarray size (from 1 to k)
        for (int start = 0; start < n; start++) {
            int min = nums[start], max = nums[start];

            // For each subarray starting at 'start', consider lengths from 1 to k
            for (int end = start; end < n && end - start + 1 <= k; end++) {
                min = Math.min(min, nums[end]);
                max = Math.max(max, nums[end]);
                result += min + max;
            }
        }

        return result;
    }

    /**
     * Solution from Leetcode
     */
    public static long minMaxSubarraySum2(int[] nums, int k) {
        return minOrMaxSum(nums, k, true) + minOrMaxSum(nums, k, false);
    }

    private static long minOrMaxSum(int[] nums, int k, boolean isMin) {
        long sum = 0L;
        int n = nums.length;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(-1);

        for (int i = 0; i <= n; i++) {
            while (stack.peekFirst() != -1 &&
                    (i == n ||
                            (isMin && nums[stack.peekFirst()] >= nums[i]) ||
                            (!isMin && nums[stack.peekFirst()] <= nums[i]))) {

                int middle = stack.removeFirst();
                int left = stack.peekFirst() + 1;
                int right = i - 1;
                int minLeft = Math.max(left, middle - k + 1);
                int maxRight = Math.min(middle + k - 1, right);
                long subArrayCount = 0L;

                if (maxRight - minLeft < k) {
                    // Can ignore k in this case
                    // => subArrayCount = (numOfLeftElements + 1) * (numOfRightElements + 1)
                    subArrayCount = (middle - minLeft + 1L) * (maxRight - middle + 1L);
                } else {
                    // To optimize performance, compare the number of possible left boundaries and
                    // right boundaries. Choose the side with fewer elements to minimize computation.
                    if (middle - minLeft < maxRight - middle) {
                        // Count subarrays starting from l
                        for (int l = minLeft; l <= middle; l++) {
                            int maxR = Math.min(right, l + k - 1);
                            subArrayCount += Math.max(maxR - middle + 1, 0);
                        }
                    } else {
                        // Count subarrays ending at r
                        for (int r = middle; r <= maxRight; r++) {
                            int minL = Math.max(r - k + 1, left);
                            subArrayCount += Math.max(middle - minL + 1, 0);
                        }
                    }
                }

                sum += subArrayCount * nums[middle];
            }
            stack.addFirst(i);
        }

        return sum;
    }
}
