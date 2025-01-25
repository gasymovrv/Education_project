package examples.multithreading.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Task(i));
        }
        System.out.println("All tasks submitted");
        System.out.println("isShutdown() = " + executorService.isShutdown());
        System.out.println("isTerminated() = " + executorService.isTerminated());

        // Инициирует упорядоченное завершение работы,
        // при котором ранее отправленные задачи выполняются, но новые задачи не принимаются.
        // Позволяет после вызова awaitTermination() вернуться в текущей поток
        // сразу после выполнения последней засабмиченной таски даже если таймаут не наступил.
        executorService.shutdown();
        System.out.println("After shutdown:");
        System.out.println("\tisShutdown() = " + executorService.isShutdown());
        System.out.println("\tisTerminated() = " + executorService.isTerminated());

        // Что-то типа join, ждем наступления первого из:
        // 1. Все задачи выполнились
        // 2. Истек таймаут
        // 3. Interruption текущего потока
        var isTerminated = executorService.awaitTermination(15, TimeUnit.SECONDS);

        System.out.println("After termination:");
        System.out.println("\tisTerminated() = " + isTerminated);
        System.out.println("All tasks completed");
    }
}

class Task implements Runnable {

    private final int id;

    public Task(int id) {
        this.id = id;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------- Task " + id + " was completed -------");
    }
}
