package examples;

import java.util.Arrays;

class MainTest {

  public int removeElement(int[] nums, int val) {
    int count = 0;
    int removeCount = 0;
    for (int i = 0; i < nums.length - removeCount; i++) {
      var temp = nums[i];

      if (temp == val) {
        for (int j = (nums.length - 1) - removeCount; j > i; j--) {
          if (nums[j] != val) {
            nums[i] = nums[j];
            nums[j] = temp;
            count++;
            break;
          }
        }
        removeCount++;
      } else {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    //final int[] array = {0, 1, 2, 2, 3, 0, 4, 2};
    //final int[] array = {3,2,2,3};
    final int[] array = {4,5};
    System.out.println(new MainTest().removeElement(array, 4));
    System.out.println(Arrays.toString(array));
  }
}
