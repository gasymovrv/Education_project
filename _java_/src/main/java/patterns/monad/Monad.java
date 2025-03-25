package patterns.monad;

import java.util.function.Function;

public class Monad<T> {

    private T value;

    private Monad(T value) {
        this.value = value;
    }

    public static <T> Monad<T> from(T value) {
        return new Monad<>(value);
    }

    public <R> Monad<R> map(Function<T, R> func) {
        return new Monad<>(func.apply(value));
    }
}
