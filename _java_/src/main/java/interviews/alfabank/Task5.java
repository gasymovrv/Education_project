package interviews.alfabank;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//5. За какое время выполнится программа на машине с 1 cpu без HT?
public class Task5 {

    static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println("Time taken: " + (System.currentTimeMillis() - start))));
        try (var pool = new ThreadPoolExecutor(
                /*core pool size*/ 2,
                /*maximum pool size*/ 10,
                /*keepAliveTime value and unit*/ 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10))) { // 10 означает: одновременно в очереди может находиться максимум 10 задач, которые ждут, пока их возьмёт поток из пула.

            for (int i = 0; i < 10; i++) {
                pool.execute(Task5::sleepOneSecond);

                //а если вот так?
                //var t = new Thread(Task5::sleepOneSecond);
                //t.setDaemon(true);
                //pool.execute(t);
            }
        }
    }

}

//Около 5 секунд.
//
//Обоснование:
//Вы отправляете 10 задач, каждая спит 1 с.
//ThreadPoolExecutor с corePoolSize=2 и очередью на 10 элементов не будет расти до maximumPoolSize=10, потому что очередь не переполняется. Одновременно работают только 2 рабочих потока.
//Итог: ceil(10/2) * 1 с ≈ 5 с плюс небольшой оверхед.
//
//Замечания по коду:
//Передача new Thread(Pool::sleepOneSecond) в execute лишь использует Thread как Runnable; setDaemon(true) эффекта не имеет, поток не стартует.
//Если нужна ~1 с “стенка-в-стенку”, задайте corePoolSize=10 и/или используйте маленькую очередь (ArrayBlockingQueue<>(1)) либо Executors.newFixedThreadPool(10).
