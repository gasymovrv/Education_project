package examples.algorithms.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * <p>
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        //System.out.println(subarraySum(new int[]{1, 1, 1}, 2)); // Output: 2
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3)); // Output: 2
        System.out.println(subarraySum(new int[]{-1, -1, 1}, 0)); // Output: 2
    }

    public static int subarraySum(int[] nums, int k) {
        // Map to store prefix sums and their frequencies
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Initialize for the case when subarray starts at index 0

        int count = 0;  // Total number of subarrays found
        int currentSum = 0; // Current prefix sum

        // Iterate through the array
        for (int num : nums) {
            currentSum += num; // Update prefix sum

            // Ни хрена не понятно че тут происходит, пытался въехать часа 3, но так и не понял.
            // Но работает.
            // Описание от чата:
            //Key Idea is a subarray sum from index `i` to `j` is the difference between two prefix sums:
            //  subarraySum(i, j) = prefixSum(j) - prefixSum(i-1)
            //  where:
            //  - prefixSum(j) is the sum of elements from the start of the array to index j.
            //  - prefixSum(i-1) is the sum of elements before the start of the subarray.
            //
            //To find subarrays with sum k, rearrange this equation:
            //  prefixSum(j) - prefixSum(i-1) = k
            //  prefixSum(i-1) = prefixSum(j) - k
            //
            //Thus, if the difference "currentSum - k" (where currentSum = prefixSum(j)) exists in the map, it means there is a prior prefix sum at index i-1, such that the subarray between i and j has a sum of k.
            //
            //Example Walkthrough
            //Given `nums = [1, 1, 1]` and `k = 2`:
            //1. At index 1:
            //   - `currentSum = 2`
            //   - Check if `currentSum - k = 2 - 2 = 0` exists in `prefixSumMap`. It does (initial value `{0=1}`), meaning there is a subarray from the start to this index with sum 2.
            //
            //2. At index 2:
            //   - `currentSum = 3`
            //   - Check if `currentSum - k = 3 - 2 = 1` exists in `prefixSumMap`. It does, meaning a subarray starting at index 1 and ending at index 2 has sum 2.
            //
            //By maintaining the prefix sum frequencies in the map, this approach efficiently identifies all subarrays whose sum equals k.
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }

            // Update prefix sum frequency in the map
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
