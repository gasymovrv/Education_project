package examples.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/two-sum/description/
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such
 * that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 10^4 -10^9 <= nums[i] <= 10^9 -10^9 <= target <= 10^9 Only one valid answer
 * exists.
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6)));
    }

    public static int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        // Сохраняем разницу между элементом и целевой суммой в мапу как ключ и индекс этого элемента как значение
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            map.put(complement, i);
        }

        for (int i = 0; i < nums.length; i++) {
            final Integer complementIndex = map.get(nums[i]);
            // Если нашли недостающую разницу (по ключу в мапе - nums[i]) до целевой суммы
            // и при этом индекс этого элемента (значение в мапе - complementIndex) не равен текущему индексу,
            // то мы нашли, то что искали
            if (complementIndex != null && complementIndex != i) {
                return new int[]{i, complementIndex};
            }
        }
        return null;
    }
}
