package examples.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 * <p>
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]] Explanation: Since
 * intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * Example 2:
 * Input: intervals = [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5] are
 * considered overlapping.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 10^4 intervals[i].length == 2 0 <= starti <= endi <= 10^4
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};

        for (int[] interval : merge(intervals1)) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();
        for (int[] interval : merge(intervals2)) {
            System.out.print(Arrays.toString(interval) + " ");
        }
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] currentInterval = intervals[0];
        int currentEnd = currentInterval[1];

        var merged = new ArrayList<int[]>();
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= currentEnd) {
                currentEnd = Math.max(currentEnd, interval[1]);
                currentInterval[1] = currentEnd;
            } else {
                currentEnd = interval[1];
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }
}
