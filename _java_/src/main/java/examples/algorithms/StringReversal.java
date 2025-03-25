package examples.algorithms;

public class StringReversal {
    /**
     * Переворачивает строку задом наперед без использования встроенных функций реверса.
     * <p>
     * Временная сложность: O(n) — мы проходим по массиву не более одного раза, выполняя O(1) операций для каждой пары символов.
     * <p>
     * Пространственная сложность: O(n) — требуется дополнительный массив char[] длины n
     *
     * @param s входная строка
     * @return перевернутая строка
     */
    public static String reverseString(String s) {
        char[] chars = s.toCharArray(); // Преобразуем строку в массив символов
        int left = 0;
        int right = chars.length - 1;

        // Меняем местами символы, пока не дойдем до середины строки
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars); // Создаем новую строку из массива символов
    }

    public static void main(String[] args) {
        String input = "hello";
        String reversed = reverseString(input);
        System.out.println(reversed); // Вывод: "olleh"
    }
}
