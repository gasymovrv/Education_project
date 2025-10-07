package interviews.vk;

import java.util.Arrays;

/**
 Дан массив целых чисел, повторяющихся элементов в массиве нет.
 Нужно преобразовать в строку, сворачивая соседние по числовому ряду числа в диапазоны.

 Input: [1,4,5,2,3,9,8,11,0,13]
 Output: "0-5,8-9,11,13"
 */
public class VK1 {
    public static String compress(int[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        Arrays.sort(array);
        int prev = array[0];
        int rangeStart = array[0];
        var sb = new StringBuilder();

        for (int i = 1; i < array.length; i++) {
            if (array[i] > prev + 1) {
                appendRange(sb, rangeStart, prev);
                rangeStart = array[i];
            }
            prev = array[i];
        }
        appendRange(sb, rangeStart, prev);

        return sb.toString();
    }

    private static void appendRange(StringBuilder sb, int start, int end) {
        if (sb.length() > 0) {
            sb.append(",");
        }
        if (start == end) {
            sb.append(start);
        } else {
            sb.append(start).append("-").append(end);
        }
    }

    public static void main(String[] args) {
        System.out.println(compress(new int[] {1,4,5,2,3,9,8,11,0,13}));
    }
}
