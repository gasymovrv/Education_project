package examples.java8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class ClosureTest {
    public static void main(String[] args) {
        Supplier<Integer> closure1 = nonWorkingClosure();
        System.out.println(closure1.get());
        System.out.println(closure1.get());
        System.out.println(closure1.get());

        System.out.println();

        Supplier<Integer> closure2 = fixedClosure();
        System.out.println(closure2.get());
        System.out.println(closure2.get());
        System.out.println(closure2.get());
    }

    /**
     * Замыкание не работает,
     * т.к. необходимо чтобы локальные переменные,
     * используемые в лямбде были final (из-за удаления их из стековой памяти)
     *
     * @return функция
     */
    private static Supplier<Integer> nonWorkingClosure() {
        int counter = 0;
        return () -> counter/*++*/;
    }

    /**
     * Можно имитировать замыкание с помощтю объекта,
     * т.к. он будет в куче и не удалится после отработки метода closureFix
     *
     * @return функция
     */
    private static Supplier<Integer> fixedClosure() {
        AtomicInteger ai = new AtomicInteger(0);
        return ai::incrementAndGet;
    }
}
