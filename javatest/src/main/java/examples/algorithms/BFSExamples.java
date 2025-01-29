package examples.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Главная идея BFS (поиск в ширину):
 * <p>
 * Сначала посещаем все вершины на одном уровне, затем переходим на следующий.
 */
class BFSExamples {
    public static void main(String[] args) {
        // Создаем граф (список смежности)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(4, 5));
        graph.put(3, Arrays.asList(6));
        graph.put(4, Arrays.asList());
        graph.put(5, Arrays.asList(6));
        graph.put(6, Arrays.asList());

        // Запускаем DFS от вершины 1
        System.out.println("BFS обход:");
        bfs(graph, 1);
    }

    /**
     * Обходит граф в ширину (BFS), начиная с указанной вершины.
     *
     * <ul>
     *     <li>Используем очередь (Queue), чтобы обрабатывать узлы по порядку</li>
     *     <li>Храним посещенные вершины в множестве (Set), чтобы избежать повторного посещения</li>
     *     <li>Пока очередь не пуста:
     *         <ul>
     *              <li>Извлекаем вершину, обрабатываем ее</li>
     *              <li>Добавляем всех непосещенных соседей в очередь</li>
     *          </ul>
     *     </li>
     * </ul>
     *
     * @param graph Граф в виде списка смежности
     * @param start Начальная вершина
     */
    public static void bfs(Map<Integer, List<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start); // Добавляем стартовую вершину в очередь
        visited.add(start); // Отмечаем как посещенную

        while (!queue.isEmpty()) {
            int node = queue.poll(); // Достаем вершину из очереди
            System.out.print(node + " "); // Выводим ее

            // Перебираем всех соседей
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor); // Добавляем в очередь
                    visited.add(neighbor); // Отмечаем как посещенную
                }
            }
        }
    }
}
