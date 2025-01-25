package examples.algorithms.yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinCitiesDistance {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            int[][] cities = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] coordinates = reader.readLine().split(" ");
                cities[i][0] = Integer.parseInt(coordinates[0]);
                cities[i][1] = Integer.parseInt(coordinates[1]);
            }

            long maxDistance = Long.parseLong(reader.readLine());
            String[] route = reader.readLine().split(" ");
            int startCity = Integer.parseInt(route[0]) - 1;
            int endCity = Integer.parseInt(route[1]) - 1;

            System.out.println(findMinRoads(n, cities, maxDistance, startCity, endCity));
        }
    }

    private static int findMinRoads(int n, int[][] cities, long maxDistance, int start, int end) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < n; next++) {
                if (!visited[next] && manhattanDistance(cities[current], cities[next]) <= maxDistance) {
                    visited[next] = true;
                    distance[next] = distance[current] + 1;
                    queue.add(next);

                    if (next == end) {
                        return distance[next];
                    }
                }
            }
        }

        return -1;
    }

    private static int findMinRoads2(int n, int[][] cities, long maxDistance, int start, int end) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];

        Arrays.fill(distance, -1);
        queue.add(start);
        visited[start] = true;
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < n; next++) {
                if (!visited[next] && manhattanDistance(cities[current], cities[next]) <= maxDistance) {
                    visited[next] = true;
                    distance[next] = distance[current] + 1;
                    queue.add(next);

                    if (next == end) {
                        return distance[next];
                    }
                }
            }
        }

        return -1;
    }

    private static long manhattanDistance(int[] city1, int[] city2) {
        return Math.abs(city1[0] - city2[0]) + Math.abs(city1[1] - city2[1]);
    }
}
