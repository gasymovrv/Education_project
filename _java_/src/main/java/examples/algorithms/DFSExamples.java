package examples.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Главная идея DFS (поиск в глубину):
 * <p>
 * - Используем рекурсию или стек, чтобы углубляться в граф.
 * <p>
 * - Начинаем с начальной вершины, помечаем её как посещённую.
 * <p>
 * - Рекурсивно (или используя стек) переходим к соседним вершинам, пока не достигнем "конца пути".
 * <p>
 * - После полного изучения одного пути возвращаемся назад и ищем другие пути.
 * <p>
 * Разберем на примере графа:
 * <p>
 * 1 -> 2, 3
 * <p>
 * 2 -> 4, 5
 * <p>
 * 3 -> 6
 * <p>
 * 4 -> нет соседей
 * <p>
 * 5 -> 6
 * <p>
 * 6 -> нет соседей
 * <p>
 * DFS обход:
 * <p>
 * Начинаем с 1, переходим к 2 (левый путь).
 * <p>
 * Идём глубже: 2 -> 4 (нет соседей).
 * <p>
 * Возвращаемся к 2 -> 5 -> 6 (нет соседей).
 * <p>
 * Возвращаемся к 1, идём в 3 -> 6 (но 6 уже посещен).
 */
class DFSExamples {
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
        System.out.println("DFS рекурсивный обход:");
        dfsRecursive(graph, 1, new HashSet<>());

        System.out.println();

        // Запускаем DFS от вершины 1
        System.out.println("DFS итеративный обход:");
        dfsIterative(graph, 1);
    }

    /**
     * Рекурсивный DFS.
     *
     * @param graph   Граф в виде списка смежности
     * @param node    Текущая вершина
     * @param visited Множество для отслеживания посещённых вершин
     */
    public static void dfsRecursive(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        if (visited.contains(node)) {
            return; // Если вершина уже посещена, выходим
        }

        System.out.print(node + " "); // Выводим вершину
        visited.add(node); // Помечаем как посещённую

        // Рекурсивно посещаем всех соседей
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            dfsRecursive(graph, neighbor, visited);
        }
    }

    /**
     * Итеративный DFS
     *
     * @param graph Граф в виде списка смежности
     * @param start Начальная вершина
     */
    public static void dfsIterative(Map<Integer, List<Integer>> graph, int start) {
        Deque<Integer> stack = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop(); // Берём вершину из стека
            System.out.print(node + " "); // Выводим вершину
            visited.add(node); // Помечаем как посещённую

            // Добавляем всех соседей в стек в ОБРАТНОМ порядке
            List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }
}
