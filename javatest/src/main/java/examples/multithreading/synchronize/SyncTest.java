package examples.multithreading.synchronize;

import java.util.concurrent.atomic.AtomicInteger;

public class SyncTest {
    private int counter;
    private int synchronizedCounter;
    private AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        syncTest.doWork();
    }

    public synchronized void increment() {
        synchronizedCounter++;
    }


    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                //Не атомарная операция, получаем race condition
                counter = counter + 1;

                //используем synchronized, потоки по сути будут работать последовательно
                increment();

                //используем AtomicInteger - быстрее чем синхронизация
                atomicCounter.incrementAndGet();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                //Не атомарная операция, получаем race condition
                counter = counter + 1;

                //используем synchronized, потоки по сути будут работать последовательно
                increment();

                //используем AtomicInteger - быстрее чем синхронизация
                atomicCounter.incrementAndGet();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
        System.out.println(atomicCounter);
        System.out.println(synchronizedCounter);
    }
}