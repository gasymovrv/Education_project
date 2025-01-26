package examples.algorithms.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode.com/problems/count-mentions-per-user
 *
 * Дано число numberOfUsers представляющее общее число пользователей.
 * Дан список events размера n, каждый элемент которого тоже список типа String фиксированным размером 3.
 *
 * Каждый events[i] может быть одним из двух типов:
 * - Message Event: ["MESSAGE", "timestamp_i", "mentions_string_i"]
 *     - Т.е. событие о том что набор юзеров был упомянут на timestamp_i.
 *     - mentions_string_i может содержать один из следующих символов:
 *         - id<number> где <number> - число в диапазоне [0, numberOfUsers - 1]. Может быть много таких id, раздреленных одним символом пробела и могут быть дубликаты. Может упоминать и offline юзеров.
 *         - ALL - упомянуть всех юзеров
 *         - HERE - упомянуть online юзеров
 * - Offline Event: ["OFFLINE", "timestamp_i", "id_i"]
 *     - Это событие о том что юзер id_i стал offline на timestamp_i на 60 единиц времени. Юзер автоматически будет online в timestamp_i + 60
 *
 * Вернуть массив mentions, где mentions[i] представляют количество упоминаний пользователя с id<number> во всех событиях MESSAGE.
 *
 * Все пользователи изначально подключены к сети, и если пользователь переходит в offline  или возвращается в online, изменение его статуса обрабатывается ДО обработки любого события сообщения, которое происходит в тот же момент времени.
 *
 * Обрати внимание, что пользователь может быть упомянут несколько раз в одном сообщении, и каждое упоминание должно учитываться отдельно.
 */
public class CountMentionsPerUser {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        // Результат - массив для подсчета упоминаний каждого пользователя
        int[] mentions = new int[numberOfUsers];

        // Множество для отслеживания offline пользователей
        Set<Integer> offlineUsers = new HashSet<>();

        // Очередь для управления временем offline
        PriorityQueue<int[]> offlineQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Сортировка событий по timestamp и типу события (OFFLINE перед MESSAGE на одном timestamp)
        events.sort((a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            if (timeA != timeB) {
                return timeA - timeB;
            }
            if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) {
                return -1;
            }
            if (a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) {
                return 1;
            }
            return 0;
        });

        // Итерация по событиям
        for (List<String> event : events) {
            String eventType = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));

            // Обрабатываем истечение времени offline
            while (!offlineQueue.isEmpty() && offlineQueue.peek()[1] <= timestamp) {
                offlineUsers.remove(offlineQueue.poll()[0]);
            }

            if (eventType.equals("OFFLINE")) {
                int userId = Integer.parseInt(event.get(2));
                if (!offlineUsers.contains(userId)) {
                    offlineUsers.add(userId);
                    offlineQueue.add(new int[]{userId, timestamp + 60});
                }
            } else if (eventType.equals("MESSAGE")) {
                String mentionsString = event.get(2);
                Map<Integer, Integer> mentionCount = new HashMap<>();

                if (mentionsString.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        mentionCount.put(i, mentionCount.getOrDefault(i, 0) + 1);
                    }
                } else if (mentionsString.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (!offlineUsers.contains(i)) {
                            mentionCount.put(i, mentionCount.getOrDefault(i, 0) + 1);
                        }
                    }
                } else {
                    String[] ids = mentionsString.split(" ");
                    for (String id : ids) {
                        if (id.startsWith("id")) {
                            int userId = Integer.parseInt(id.substring(2));
                            mentionCount.put(userId, mentionCount.getOrDefault(userId, 0) + 1);
                        }
                    }
                }

                for (Map.Entry<Integer, Integer> entry : mentionCount.entrySet()) {
                    mentions[entry.getKey()] += entry.getValue();
                }
            }
        }

        return mentions;
    }

    public static void main(String[] args) {
        CountMentionsPerUser counter = new CountMentionsPerUser();
        int numberOfUsers = 3;
        List<List<String>> events = Arrays.asList(
                Arrays.asList("MESSAGE", "2", "HERE"),
                Arrays.asList("OFFLINE", "2", "1"),
                Arrays.asList("OFFLINE", "1", "0"),
                Arrays.asList("MESSAGE", "61", "HERE")
        );

        int[] result = counter.countMentions(numberOfUsers, events);
        System.out.println(Arrays.toString(result)); // Ожидается: [1, 0, 2]

        numberOfUsers = 5;
        events = Arrays.asList(
                Arrays.asList("OFFLINE", "28", "1"),
                Arrays.asList("OFFLINE", "14", "2"),
                Arrays.asList("MESSAGE", "2", "ALL"),
                Arrays.asList("MESSAGE", "28", "HERE"),
                Arrays.asList("OFFLINE", "66", "0"),
                Arrays.asList("MESSAGE", "34", "id2"),
                Arrays.asList("MESSAGE", "83", "HERE"),
                Arrays.asList("MESSAGE", "40", "id3 id3 id2 id4 id4")
        );

        result = counter.countMentions(numberOfUsers, events);
        System.out.println(Arrays.toString(result)); // Ожидается: [2, 1, 4, 5, 5]
    }
}
