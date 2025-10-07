package interviews.vk;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;

/**
 * В данном классе урезанная версия задачи - только сервисный слой и тест многопоточки.
 * <p>
 * Контекст
 * <p>
 * Есть лента с карточками публикаций (Дзен). Пользователи кликают в публикации, которые их заинтересовали.
 * У каждой публикации есть автор. Хотим на карточках показывать сколько людей кликнуло в публикации этого автора за предыдущий день.
 * <p>
 * Задача
 * <p>
 * Нужно разработать сервис, который будет считать количество уникальных пользователей, кликнувших в любую публикацию автора за календарные сутки.
 * Сутки считаем по времени сервера, с 0:00 предыдущего дня до 0:00 текущего.
 * <p>
 * - Каждый раз, когда кто-то кликает в публикацию, клиент отправляет запрос с id пользователя и автора.
 * <p>
 * - Когда мы собираем ленту рекомендаций для очередного пользователя - в наш сервис поступает запрос со списком авторов, для которых мы хотим посчитать метрику.
 * <p>
 * Нужно написать хороший код, пригодный для production, как будто это реальная рабочая задача.
 * <p>
 * Для упрощения считаем, что сервис работает на одной машинке, необходимые данные помещаются в память.
 */
public class VK2 {
    static class AuthorClickService {
        private Instant today() {
            return Instant.now().truncatedTo(ChronoUnit.DAYS);
        }

        @Getter
        private final Map<AuthorByDay, Set<Integer>> uniqueUsersByAuthor = new ConcurrentHashMap<>();

        @Getter
        private final Map<AuthorByDay, Integer> clicksByAuthor = new ConcurrentHashMap<>();

        // My interview solution
        public synchronized void addClick(ClickEventDto event) {
            var today = today();
            var key = new AuthorByDay(event.authorId(), today);

            var count = uniqueUsersByAuthor.merge(
                    key,
                    new HashSet<>(Set.of(event.userId())),
                    (existingSet, newSet) -> {
                        existingSet.addAll(newSet);
                        return existingSet;
                    }
            );
            clicksByAuthor.put(key, count.size());
        }

        // Thread-safe without synchronized, inner Set is a concurrent version (non-atomic)
        public void addClickThreadSafeNonAtomic(ClickEventDto event) {
            var today = today();
            var key = new AuthorByDay(event.authorId(), today);

            uniqueUsersByAuthor
                    .computeIfAbsent(key, k -> ConcurrentHashMap.newKeySet())
                    .add(event.userId());

            clicksByAuthor.put(key, uniqueUsersByAuthor.get(key).size());
        }

        // Thread-safe without synchronized, inner Set is a regular HashSet (atomic)
        public void addClickThreadSafeAtomic(ClickEventDto event) {
            var today = today();
            var key = new AuthorByDay(event.authorId(), today);

            uniqueUsersByAuthor
                    .compute(key, (k, existingValue) -> {
                        var set = (existingValue == null) ? new HashSet<Integer>() : existingValue;
                        set.add(event.userId());
                        clicksByAuthor.put(key, set.size());
                        return set;
                    });
        }

        public DAUResponseDto getYesterdayActiveUsers(int[] authorList) {
            var uniqueUsersCount = new int[authorList.length];
            for (int i = 0; i < authorList.length; i++) {
                uniqueUsersCount[i] = clicksByAuthor.getOrDefault(
                        new AuthorByDay(
                                authorList[i],
                                today().minus(1, ChronoUnit.DAYS)
                        ),
                        0
                );
            }

            return new DAUResponseDto(uniqueUsersCount);
        }
    }

    public record ClickEventDto(int userId, int authorId) {
    }

    public record DAUResponseDto(int[] uniqueUsersCount) {
    }

    public record AuthorByDay(
            int authorId,
            Instant day
    ) {
    }

    public static void main(String[] args) throws InterruptedException {
        AuthorClickService authorClickService = new AuthorClickService();

        int authors = 10;
        int usersPerAuthor = 10000;

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int authorId = 1; authorId <= authors; authorId++) {
                int currentAuthor = authorId;
                for (int userId = 1; userId <= usersPerAuthor; userId++) {
                    int currentUser = userId;
                    executor.submit(() -> {
                        authorClickService.addClickThreadSafeAtomic(new ClickEventDto(currentUser, currentAuthor));
                        try {
                            Thread.sleep(10); // To emulate race conditions
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        }

        System.out.println("=== uniqueUsersByAuthor ===");
        authorClickService.getUniqueUsersByAuthor()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(a -> a.getKey().authorId))
                .forEach((entry) ->
                        System.out.printf("%s -> %d unique users%n", entry.getKey(), entry.getValue().size())
                );
        System.out.println("=== clicksByAuthor ===");
        authorClickService.getClicksByAuthor()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(a -> a.getKey().authorId))
                .forEach(entry ->
                        System.out.printf("%s -> %d clicks%n", entry.getKey(), entry.getValue())
                );
    }
}
