package examples.java8;

import io.vavr.control.Try;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExceptionTest {
    public static void main(String[] args) {
        testIgnoreWithDefault();
        System.out.println();

        testIgnore();
        System.out.println();

        testIgnoreWithDefaultVavr();
        System.out.println();

        testRethrow();
    }

    private static void testRethrow() {
        List<String> strings = Stream.of(new Person("1"), new Person("2"))
                .map(person -> tryOrElseRethrow(StreamExceptionTest::canFail, person))
                .collect(Collectors.toList());
        System.out.println("testRethrow: " + strings);
    }

    private static void testIgnoreWithDefault() {
        List<String> strings = Stream.of(new Person("1"), new Person("2"))
                .map(person -> tryGet(() -> canFail(person)).orElse("default"))
                .collect(Collectors.toList());
        System.out.println("testIgnoreWithDefault: " + strings);
    }

    private static void testIgnore() {
        List<String> strings = Stream.of(new Person("1"), new Person("2"))
                .flatMap(person -> tryGet(() -> canFail(person)).map(Stream::of).orElse(Stream.empty()))
                .collect(Collectors.toList());
        System.out.println("testIgnore: " + strings);
    }

    private static void testIgnoreWithDefaultVavr() {
        List<String> strings = Stream.of(new Person("1"), new Person("2"))
                .map(person -> Try
                        .of(() -> canFail(person))
                        .onFailure(e -> System.out.println("Vavr.Try, " + e.getMessage()))//log error
                        .getOrElse("default"))
                .collect(Collectors.toList());
        System.out.println("testIgnoreWithDefaultVavr: " + strings);
    }

    private static String canFail(Person p) throws Exception {
        if (p.getName().equals("1"))
            throw new IOException("oops");
        else
            return p.getName() + "-success";
    }

    public static <T, R> R tryOrElseRethrow(CheckedFunction<T, R> checkedFunction, T arg) {
        try {
            return checkedFunction.apply(arg);
        } catch (Exception e) {
            System.out.println("tryOrElseRethrow, " + e.getMessage());//log error
            throw new RuntimeException(e);//custom unchecked exception
        }
    }

    public static <R> R tryOrElseRethrow(CheckedSupplier<R> checkedSupplier) {
        try {
            return checkedSupplier.get();
        } catch (Exception e) {
            System.out.println("tryOrElseRethrow, " + e.getMessage());//log error
            throw new RuntimeException(e);//custom unchecked exception
        }
    }

    public static <R> Optional<R> tryGet(CheckedSupplier<R> checkedSupplier) {
        try {
            return Optional.ofNullable(checkedSupplier.get());
        } catch (Exception e) {
            System.out.println("tryGet, " + e.getMessage());//log error
            return Optional.empty();
        }
    }

    public static void tryDo(Runnable doFunc) {
        try {
            doFunc.run();
        } catch (Exception e) {
            System.out.println("tryDo, " + e.getMessage());//log error
        }
    }

    public static <T, R> Function<T, R> wrapToUnchecked(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                System.out.println(e.getMessage());//log error
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    private interface CheckedFunction<T, R> {
        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    private interface CheckedSupplier<R> {
        R get() throws Exception;
    }

    private static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
