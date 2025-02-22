package examples.algorithms.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/description/
 * <p>
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of
 * each number sorted in non-decreasing order.
 * <p>
 * Example 1:
 * Input: nums = [-4,-1,0,3,10] Output: [0,1,9,16,100] Explanation: After squaring, the array
 * becomes [16,1,0,9,100]. After sorting, it becomes [0,1,9,16,100].
 * <p>
 * Example 2: Input: nums = [-7,-3,2,3,11] Output: [4,9,9,49,121]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4 -10^4 <= nums[i] <= 10^4 nums is sorted in non-decreasing order.
 * <p>
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an
 * O(n) solution using a different approach?
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-7, -3, 2, 3, 11})));
    }

    public static int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                res[i] = nums[left] * nums[left];
                left++;
            } else {
                res[i] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }
}
