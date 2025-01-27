package examples.algorithms;

import java.util.Arrays;

/**
 * Главная идея алгоритма:
 * <p>
 * - Разделение (Divide): Делим массив пополам, рекурсивно применяя алгоритм к каждой половине.
 * Используется Arrays.copyOfRange, чтобы скопировать подмассивы. Каждый из подмассивов снова делится, пока не останется массивы длиной 1.
 * <p>
 * - Слияние (Conquer): Объединяем два отсортированных массива в один, соблюдая порядок элементов.
 * <p>
 * Сложности алгоритма:
 * <p>
 * - Временная сложность:
 * На каждом уровне рекурсии выполняется операция слияния за O(n), где n — длина массива.
 * Глубина рекурсии составляет O(log n), так как массив делится пополам.
 * Общая временная сложность: O(n log n).
 * <p>
 * - Пространственная сложность:
 * Требуется O(n) дополнительной памяти для хранения временных массивов left и right.
 * Рекурсивные вызовы занимают O(log n) памяти в стеке.
 * Общая пространственная сложность: O(n).
 */
public class MergeSort {
    // Основной метод для сортировки массива
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Базовый случай: массив с 1 элементом уже отсортирован
        }

        // Делим массив на две части
        int mid = array.length / 2; // Находим середину массива
        int[] left = Arrays.copyOfRange(array, 0, mid); // Левая половина
        int[] right = Arrays.copyOfRange(array, mid, array.length); // Правая половина

        // Рекурсивно сортируем каждую половину
        mergeSort(left);
        mergeSort(right);

        // Сливаем отсортированные половины
        merge(array, left, right);
    }

    // Метод для слияния двух отсортированных массивов
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Пока в обоих массивах есть элементы
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++]; // Берем элемент из левого массива
            } else {
                array[k++] = right[j++]; // Берем элемент из правого массива
            }
        }

        // Копируем оставшиеся элементы из левого массива (если есть)
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Копируем оставшиеся элементы из правого массива (если есть)
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Пример использования
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Исходный массив: " + Arrays.toString(array));

        mergeSort(array);

        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }

}
