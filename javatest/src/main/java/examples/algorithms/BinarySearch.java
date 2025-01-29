package examples.algorithms;

public class BinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Element found
            } else if (nums[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Element not found
    }

    public static void main(String[] args) {
        var arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(search(arr, 2));
        System.out.println(search(arr, 5));
        System.out.println(search(arr, 8));
        System.out.println(search(arr, 1));
        System.out.println(search(arr, 10));
    }
}

