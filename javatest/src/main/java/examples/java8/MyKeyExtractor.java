package examples.java8;

public class MyKeyExtractor {
    public static <R, T> void printKey(MyFunction<T, R> keyExtractor, T t) {
        System.out.println(keyExtractor.apply(t));
    }
}
