package examples.algorithms;

import java.util.Arrays;

/**
 * <b>Главная идея:</b> Элементы массива упорядочиваются поэтапно:
 * каждый элемент извлекается из неотсортированной части массива
 * и вставляется в правильное место в отсортированной части.
 * <p>
 * <b>Этапы работы:</b>
 * <ol>
 *   <li>Итерация по массиву от второго элемента до последнего.</li>
 *   <li>На каждой итерации извлекается текущий элемент.</li>
 *   <li>Этот элемент вставляется в правильное место в уже отсортированной части массива.</li>
 * </ol>
 *
 * <b>Сложности:</b>
 * <ul>
 *   <li><b>Временная сложность:</b>
 *   <ul>
 *     <li>Лучший случай: O(n), если массив уже отсортирован.</li>
 *     <li>Средний и худший случай: O(n^2), так как элементы перемещаются для вставки.</li>
 *   </ul>
 *   </li>
 *   <li><b>Пространственная сложность:</b>
 *   <ul>
 *     <li>O(1), так как сортировка выполняется "на месте".</li>
 *   </ul>
 *   </li>
 * </ul>
 *
 * <b>Отличительные особенности:</b>
 * <ul>
 *   <li>Простота реализации.</li>
 *   <li>Эффективность на небольших массивах или почти отсортированных данных.</li>
 *   <li>Устойчивость: сохраняет порядок одинаковых элементов.</li>
 * </ul>
 */
public class InsertionSort {
    /**
     * Сортирует массив целых чисел с использованием алгоритма вставками.
     *
     * @param array массив для сортировки
     */
    public static void insertionSort(int[] array) {
        // Итерируемся по всем элементам, начиная со второго
        for (int i = 1; i < array.length; i++) {
            int key = array[i]; // Текущий элемент, который нужно вставить
            int j = i - 1;

            // Сдвигаем элементы массива вправо, чтобы освободить место для вставки
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            // Вставляем текущий элемент на правильное место
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {29, 10, 14, 37, 13};
        System.out.println("Исходный массив: " + Arrays.toString(array));
        insertionSort(array);

        System.out.println("Отсортированный массив: " +  Arrays.toString(array));
    }
}
