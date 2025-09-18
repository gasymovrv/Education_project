package interviews.yandex;

/**
 * Написать алгоритм сжатия. То есть приходит строка из латиницы, повторяющиеся символы сократить до символ + число повторений
 * Пример:
 * Input: AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB
 * Output: A4B3C2XYZD4E3F3A6B28
 */
public class Yandex2 {

    /**
     * - Мы начинаем с первого символа строки и сравниваем его с последующим.
     * Если они одинаковы, увеличиваем счётчик.
     * <p>
     * Если встречаем другой символ, добавляем текущий символ и его количество (если оно больше 1) в результирующую строку (используем StringBuilder для конкатенации).
     * <p>
     * В конце, после завершения цикла, не забываем добавить последний символ и его количество в результат.
     * <p>
     * Временная сложность:
     * <p>
     * O(n) - Мы проходим по строке только один раз, где n — длина строки.
     * <p>
     * Пространственная сложность:
     * <p>
     * O(n) - Мы используем дополнительную память для результата, которая может быть в худшем случае такой же длины, как исходная строка.
     */
    public static String compress(String input) {
        // Проверка на пустую строку
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Используем StringBuilder для эффективного построения результата
        StringBuilder result = new StringBuilder();

        // Инициализация переменных
        int count = 1; // Начинаем с первого символа
        char currentChar = input.charAt(0); // Первый символ

        // Проходим по строке, начиная с второго символа
        for (int i = 1; i < input.length(); i++) {
            char nextChar = input.charAt(i);

            // Если текущий символ совпадает с предыдущим, увеличиваем счетчик
            if (nextChar == currentChar) {
                count++;
            } else {
                // Если символы разные, добавляем текущий символ и его счетчик в результат
                result.append(currentChar);
                if (count > 1) {
                    result.append(count); // Добавляем число только если оно больше 1
                }
                // Обновляем текущий символ и сбрасываем счетчик
                currentChar = nextChar;
                count = 1;
            }
        }

        // После завершения цикла добавляем последний символ и его счетчик, т.к. блок else внутри цикла не сработает когда строка закончится
        result.append(currentChar);
        if (count > 1) {
            result.append(count);
        }

        // Возвращаем результат в виде строки
        return result.toString();
    }

    public static void main(String[] args) {
        // Пример теста
        String input = "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB";
        String compressed = compress(input);
        System.out.println(compressed); // Ожидаемый результат: A4B3C2XYZD4E3F3A6B28
    }
}
