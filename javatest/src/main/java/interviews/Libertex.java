package interviews;

import java.util.Arrays;

public class Libertex {

  public static void main(String[] args) {
    int[] testArray = {3, 9, 50, 15, 99, 7, 98, 65};
    int result = distClosestNumbers(testArray);
    // Expected result is 1 (the 2 closest numbers are 98 and 99)
    System.out.println(result);
  }

  // Returns the distance between the two closest numbers.
  private static int distClosestNumbers(int[] numbers) {
    // try to implement it!
    if (numbers.length < 2) {
      return 0;
    }
    // O(n log(n))
    Arrays.sort(numbers);
    var preLastEl = numbers[numbers.length - 2];
    var lastEl = numbers[numbers.length - 1];
    return lastEl - preLastEl;
  }
}
