package examples.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ConcurrentTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //futureTest1();
        //futureTest2();
        futureTest3();
    }

    /**
     * Выполнение асинхронных задач с использованием runAsync()
     */
    public static void futureTest1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            doSomeTime(1);
            System.out.println("Я буду работать в отдельном потоке, а не в главном.");
        });

        System.out.println("Делаем что-то...\n".repeat(20));

        // Блокировка и ожидание завершения Future
        future.get();
    }

    /**
     * Выполнение асинхронной задачи и возврат результата с использованием supplyAsync()
     */
    public static void futureTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            doSomeTime(1);
            return "Результат асинхронной задачи";
        });

        System.out.println("Делаем что-то...\n".repeat(20));

        // Блокировка и ожидание завершения Future
        String result = future.get();
        System.out.println(result);
    }

    /**
     * Несколько последовательных преобразований с thenApply()
     */
    public static void futureTest3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            doSomeTime(1);
            return "Rajeev";
        })
                .thenApply(name -> "Привет, " + name)
                .thenApply(greeting -> greeting + ". Добро пожаловать в блог CalliCoder");

        // Выводит: Привет, Rajeev. Добро пожаловать в блог CalliCoder
        System.out.println(welcomeText.get());
    }

    private static void doSomeTime(int time) {
        try {
            // Имитация длительной работы
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
