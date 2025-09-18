package interviews.alfabank;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.getLogger;

/**
 * <b>CountDownLatch</b>
 * <ul>
 *   <li>Конструируется с целым числом <code>n</code>.</li>
 *   <li>Потоки, вызывающие {@link java.util.concurrent.CountDownLatch#await()},
 *       блокируются, пока счётчик &gt; 0.</li>
 *   <li>Каждый вызов {@link java.util.concurrent.CountDownLatch#countDown()} уменьшает счётчик на 1.</li>
 *   <li>Когда счётчик достигает 0, все ожидающие потоки разблокируются.</li>
 *   <li>Счётчик нельзя увеличить повторно. Это одноразовый барьер.</li>
 * </ul>
 * <p><b>Применение:</b> «подождать, пока завершатся <code>n</code> задач»
 * или «дать старт после инициализации».</p>
 * <br/>
 * <b>ReentrantLock</b>
 * <ul>
 *   <li>Замена <code>synchronized</code>, но с расширенным управлением.</li>
 *   <li>Вызов {@link java.util.concurrent.locks.ReentrantLock#lock()} захватывает замок.
 *       Если он уже захвачен другим потоком — блокировка.</li>
 *   <li>Тот же поток может взять замок повторно (реентерабельность).
 *       Нужно ровно столько же раз вызвать {@link java.util.concurrent.locks.ReentrantLock#unlock()}.</li>
 *   <li>{@link java.util.concurrent.locks.ReentrantLock#tryLock()} — не блокирует,
 *       а сразу сообщает, удалось ли захватить.</li>
 *   <li>{@link java.util.concurrent.locks.ReentrantLock#lockInterruptibly()} — позволяет прерывать ожидание.</li>
 *   <li>{@link java.util.concurrent.locks.ReentrantLock#newCondition()} — создаёт объект
 *       {@link java.util.concurrent.locks.Condition} для управления ожиданием/сигналами внутри захваченного замка.</li>
 * </ul>
 * <p><b>Применение:</b> более гибкий контроль синхронизации, чем <code>synchronized</code>.</p>
 */

//6. провести ревью - всё ли здесь хорошо?
public class Task6 {
    private static final System.Logger log = getLogger(Task6.class.getName());
    private static volatile boolean ready = false;
    private static final Lock rLock = new ReentrantLock();
    private static final Condition readyCondition = rLock.newCondition();

    private static void waitAndLog() {
        try {
            rLock.lock();
            log.log(INFO, "rLock acquired, ready: {0}", ready);
            if (!ready)
                readyCondition.await();
            log.log(INFO, "ready was awaited: {0}", ready);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(Task6::waitAndLog).start();
        try {
            rLock.lock();
            ready = true;
            log.log(INFO, "signal about ready");
            readyCondition.signal();
        } finally {
            rLock.unlock();
        }
    }
}

class Task6Reviewed {
    private static final System.Logger log = System.getLogger(Task6.class.getName());
    private static boolean ready = false; // volatile не нужен
    private static final Lock lock = new ReentrantLock();
    private static final Condition readyCondition = lock.newCondition();

    private static void waitAndLog() {
        lock.lock();
        try {
            log.log(INFO, "lock acquired, ready: {0}", ready);
            while (!ready) {                // защита от ложных пробуждений
                readyCondition.await();
            }
            log.log(INFO, "ready observed: {0}", ready);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // корректная обработка прерывания
            log.log(INFO, "thread interrupted while waiting");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(Task6Reviewed::waitAndLog).start();

        lock.lock();
        try {
            ready = true;
            log.log(INFO, "signalling ready");
            readyCondition.signal(); // ok для одного ожидателя; иначе signalAll()
        } finally {
            lock.unlock();
        }
    }
}

//Альтернатива с CountDownLatch:
class Task6Latch {
    private static final System.Logger log = System.getLogger(Task6Latch.class.getName());
    private static final CountDownLatch ready = new CountDownLatch(1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                log.log(INFO, "waiting...");
                ready.await();
                log.log(INFO, "ready observed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        log.log(INFO, "signalling ready");
        ready.countDown();
    }
}
