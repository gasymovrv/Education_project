package examples.algorithms.leetcode;

/**
 * https://leetcode.com/problems/number-of-islands
 *
 * Дан двумерный массив char[][] grid, размером m на n.
 * Значениями ячеек может быть '1' или '0'. 1 - считаем землей, 2 - водой.
 * Необходимо вернуть число островов.
 * Островом считается участок по вертикали и горизонтали оруженный водой.
 * Все что за пределами ячеек массива считаем водой
 */
public class NumberOfIslands {
    /**
     * Подсчитывает количество островов в двумерном массиве.
     * Остров - это группа соседних '1' (суша), окруженная '0' (водой).
     * Поиск выполняется с помощью DFS, "затапливая" найденные острова.
     *
     * @param grid Двумерный массив символов ('1' - суша, '0' - вода)
     * @return Количество островов в массиве
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0; // Счетчик островов
        int m = grid.length, n = grid[0].length;

        // Проходим по каждой ячейке массива
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Если нашли сушу (1), запускаем DFS и увеличиваем счетчик
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * Рекурсивный поиск в глубину (DFS), который "затапливает" остров.
     * Посещает текущую ячейку и распространяется на соседние клетки (вверх, вниз, влево, вправо).
     *
     * @param grid Двумерный массив
     * @param i    Индекс строки
     * @param j    Индекс столбца
     */
    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;

        // Проверка границ и выход при встрече воды
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // "Затапливаем" текущую клетку

        // Рекурсивно посещаем соседние клетки
        dfs(grid, i - 1, j); // вверх
        dfs(grid, i + 1, j); // вниз
        dfs(grid, i, j - 1); // влево
        dfs(grid, i, j + 1); // вправо
    }

    // Пример использования
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        NumberOfIslands solution = new NumberOfIslands();
        System.out.println("Number of islands: " + solution.numIslands(grid)); // Ожидаемый результат: 3
    }
}
