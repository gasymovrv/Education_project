package examples.multithreading.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Task(i));
        }
        System.out.println("All tasks submitted");

        Thread.sleep(5000);
        System.out.println("isShutdown() = " + executorService.isShutdown());

        // Инициирует упорядоченное завершение работы,
        // при котором ранее отправленные задачи выполняются, но новые задачи не принимаются.
        // Позволяет после вызова awaitTermination() вернуться в текущей поток
        // сразу после выполнения последней засабмиченной таски даже если таймаут не наступил.
        executorService.shutdown();

        System.out.println("shutdown() was invoked, isShutdown() = " + executorService.isShutdown());
        System.out.println("isTerminated() = " + executorService.isTerminated());

        // Что-то типа join, ждем наступления первого из:
        // 1. Все задачи выполнились
        // 2. Истек таймаут
        // 3. Interruption текущего потока
        var isTerminated = executorService.awaitTermination(15, TimeUnit.SECONDS);

        System.out.println("awaitTermination() was invoked, isTerminated = " + isTerminated);
        System.out.println("All tasks completed");
    }
}

class Task implements Runnable {
    private int id;

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