package interviews;

/**
 * Дан массив целых чисел nums. Нужно найти самый большой строго монотонный отрезок
 */
public class Yandex4 {

    /**
     * Мы проходим по массиву один раз (O(n)) и отслеживаем длину текущего строго монотонного отрезка (возрастающего или убывающего).
     * Если направление изменяется, проверяем, является ли найденный отрезок самым длинным, и начинаем новый.
     */
    public static int[] findLongestMonotonicSegment(int[] nums) {
        if (nums.length == 0) return new int[]{-1, -1}; // Пустой массив -> нет сегмента

        // Переменные для результата: [начало, конец]
        int[] res = {0, 0};
        int maxLen = 1; // Длина самого длинного отрезка

        // Длина и начало текущего возрастающего и убывающего отрезков
        int incLen = 1;
        int decLen = 1;

        // Обходим массив с 1-го элемента
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) { // Возрастающий отрезок
                incLen++; // Увеличиваем длину
                decLen = 1; // Сбрасываем убывающий отрезок
            } else if (nums[i] < nums[i - 1]) { // Убывающий отрезок
                decLen++; // Увеличиваем длину
                incLen = 1; // Сбрасываем возрастающий отрезок
            } else {
                // Сбрасываем оба счетчика при равенстве
                incLen = 1;
                decLen = 1;
            }

            // Проверяем, является ли новый отрезок самым длинным
            if (incLen > maxLen) {
                maxLen = incLen;
                res[0] = (i - maxLen) + 1;
                res[1] = i;
            }
            if (decLen > maxLen) {
                maxLen = decLen;
                res[0] = (i - maxLen) + 1;
                res[1] = i;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 1, 5, 6, 7, 8, 2, 1};
        int[] result = findLongestMonotonicSegment(nums);
        System.out.println("Самый длинный отрезок: [" + result[0] + ", " + result[1] + "]");
        // Ожидаемый вывод: [4, 8] (отрезок: 1, 5, 6, 7, 8)

        nums = new int[]{1, 2, 3, 1, 1, 1};
        result = findLongestMonotonicSegment(nums);
        System.out.println("Самый длинный отрезок: [" + result[0] + ", " + result[1] + "]");
        // Ожидаемый вывод: [0, 2] (отрезок: 1, 2, 3)
    }
}
