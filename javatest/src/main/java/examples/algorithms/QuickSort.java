package examples.algorithms;

import java.util.Arrays;

public class QuickSort {

    // Тестирование алгоритма
    public static void main(String[] args) {
        int[] array = { 10, 7, 8, 9, 1, 5 };
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(array));
    }

    // Основной метод для быстрой сортировки.
    // low и high на первом вызове - это начало и конец всего массива,
    // затем в рекурсивных вызовах это будут границы подмассивов разделенных индексами опорных элементов (pivotIndex)
    public static void quickSort(int[] array, int low, int high) {
        // Если массив содержит менее двух элементов, то он уже отсортирован, пропускаем
        if (low < high) {
            // Индекс, который указывает на разделение массива
            int pivotIndex = partition(array, low, high);

            // Рекурсивная сортировка для левой и правой части
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Метод для разделения массива по опорному элементу
    private static int partition(int[] array, int low, int high) {
        // Выбираем опорный элемент (например, последний элемент массива)
        int pivot = array[high];
        int i = low - 1;

        // Перемещаем элементы, которые меньше опорного, влево.
        // Рассмотрим 1 корневой вызов данной функции:
        // Для массива [ 10, 7, 8, 9, 1, 5 ] блок if выполнится только 1 раз при i=-1 и j=4
        // и после этого цикла он будет выглядеть как [ 1, 7, 8, 9, 10, 5 ]
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // Обмен элементов
                swap(array, i, j);
            }
        }

        // Помещаем опорный элемент в правильное место.
        // Т.к. после цикла имели массив [ 1, 7, 8, 9, 10, 5 ] и i+1=1,
        // то теперь получаем такое состояние: [ 1, 5, 8, 9, 10, 7 ], т.е. 1, 5 уже на правильных местах
        swap(array, i + 1, high);

        // Данный метод возвращает pivotIndex = 1, т.е. интервалы будут:
        // [0, 0] - quickSort(array, low, pivotIndex - 1) - пропускаем, т.к. есть if (low < high)
        // [2, 5] - quickSort(array, pivotIndex + 1, high) - тут отрезок [ 8, 9, 10, 7 ] сначала превратится в [ 7, 9, 10, 8 ]
        // и т.д. рекурсивно
        return i + 1; // Индекс опорного элемента
    }

    // Метод для обмена элементов массива
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
