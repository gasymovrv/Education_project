package examples.multithreading.examples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * Deadlock Demonstration and Fix.
 *
 * <p>Demonstrates how two threads can deadlock when acquiring locks in
 * inconsistent order, and shows two strategies for resolution.
 *
 * <h2>Production relevance</h2>
 * <ul>
 *   <li>Deadlocks occur in naive resource management.</li>
 *   <li>Understanding prevention and detection is a common interview topic.</li>
 * </ul>
 *
 * <h2>Hints</h2>
 * <ul>
 *   <li>Deadlocks occur due to circular wait conditions.</li>
 *   <li>Fix with global lock ordering or {@link java.util.concurrent.locks.ReentrantLock#tryLock}.</li>
 *   <li>Virtual threads do not prevent logical deadlocks.</li>
 * </ul>
 */
public class DeadlockDemo {
    private static final Object A = new Object();
    private static final Object B = new Object();

    private static void deadlock() {
        Thread t1 = Thread.ofVirtual().start(() -> {
            synchronized (A) {
                sleep(100);
                synchronized (B) {
                    System.out.println("t1 acquired A then B");
                }
            }
        });
        Thread t2 = Thread.ofVirtual().start(() -> {
            synchronized (B) {
                sleep(100);
                synchronized (A) {
                    System.out.println("t2 acquired B then A");
                }
            }
        });
        join(t1);
        join(t2);
        System.out.println("Deadlock did not happen");
    }

    private static void fixedWithOrdering() {
        Object first = A, second = B; // define global order
        Thread t1 = Thread.ofVirtual().start(() -> {
            synchronized (first) {
                sleep(100);
                synchronized (second) {
                    System.out.println("t1 acquired A then B");
                }
            }
        });
        Thread t2 = Thread.ofVirtual().start(() -> {
            synchronized (first) {
                sleep(100);
                synchronized (second) {
                    System.out.println("t2 acquired A then B");
                }
            }
        });
        join(t1);
        join(t2);
        System.out.println("Ordering fix completed.");
    }

    private static void fixedWithTryLock() {
        ReentrantLock l1 = new ReentrantLock();
        ReentrantLock l2 = new ReentrantLock();
        Consumer<String> func = (str) -> {
            while (true) {
                try {
                    if (l1.tryLock(50, TimeUnit.MILLISECONDS)) {
                        try {
                            if (l2.tryLock(50, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(str);
                                    break;
                                } finally {
                                    l2.unlock();
                                }
                            }
                        } finally {
                            l1.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = Thread.ofVirtual().start(() -> func.accept("t1 acquired l1 then l2"));
        Thread t2 = Thread.ofVirtual().start(() -> func.accept("t1 acquired l1 then l2"));
        join(t1);
        join(t2);
        System.out.println("tryLock fix completed.");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void join(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Demonstrating potential deadlock (threads may stall)...");
        //deadlock(); // likely stalls; uncomment to see deadlock
        fixedWithOrdering();
        fixedWithTryLock();
    }
}
