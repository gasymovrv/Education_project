package examples.algorithms.leetcode;

/**
 * https://leetcode.com/problems/single-number/description/
 * <p>
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 * <p>
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * <p>
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * Each element in the array appears twice except for one element which appears only once.
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumber(new int[]{1}));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            // XOR the current number with the accumulator.
            // XOR outputs 1 if the bits are different, and 0 if the bits are the same.
            // How this works with nums = [4, 1, 2, 1, 2]:
            // 0 ^ 4 = 4 = 0000 ^ 0100 = 0100: XOR with 0 leaves the number unchanged.
            // 4 ^ 1 = 5 = 0100 ^ 0001 = 0101: Bitwise XOR compares each bit and flips bits where they differ.
            // 5 ^ 2 = 7 = 0101 ^ 0010 = 0111: XOR with the next number continues accumulating.
            // 7 ^ 1 = 6 = 0111 ^ 0001 = 0110: Cancels out the first 1 since a ^ a = 0.
            // 6 ^ 2 = 4 = 0110 ^ 0010 = 0100: Cancels out the second 2.
            result ^= num;
        }
        return result;
    }
}
