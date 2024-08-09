package examples.rxjava;

import io.reactivex.rxjava3.core.Observable;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class RxJavaTest {

    public static void main(String[] args) throws Exception {
        //1,2
        //Observable.<BigDecimal>create(operation -> {
        //            operation.onNext(new BigDecimal("100"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("1000"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("-200"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("-300"));
        //            operation.onComplete();
        //        })
        //        .filter(d -> d.compareTo(BigDecimal.ZERO) > 0)
        //        .subscribe(s -> System.out.println("Cash deposit: " + s));
        //
        //System.out.println();
        //
        ////3
        //Observable.<BigDecimal>create(operation -> {
        //            operation.onNext(new BigDecimal("100"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("1000"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("-200"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("-300"));
        //            operation.onComplete();
        //        })
        //        .map(v -> v.multiply(new BigDecimal("100")))
        //        .subscribe(s -> System.out.println("Cents: " + s),
        //                e -> System.out.println("Error: " + e.getMessage()),
        //                () -> System.out.println("Completed"));
        //
        //System.out.println();
        //
        ////4
        //Observable.<BigDecimal>create(operation -> {
        //            operation.onNext(new BigDecimal("100"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("1000"));
        //            Thread.sleep(1000);
        //            operation.onError(new RuntimeException("error!"));
        //        })
        //        .subscribe(s -> System.out.println("Operation: " + s),
        //                e -> System.out.println("Error: " + e.getMessage()),
        //                () -> System.out.println("Completed"));
        //
        //System.out.println();
        //
        ////5
        //Observable.<BigDecimal>create(operation -> {
        //            operation.onNext(new BigDecimal("100"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("1000"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("-200"));
        //            Thread.sleep(1000);
        //            operation.onNext(new BigDecimal("-300"));
        //            operation.onComplete();
        //        })
        //        .doOnComplete(() -> System.out.println("do on complete"))
        //        .subscribe(s -> System.out.println("Operation: " + s),
        //                e -> System.out.println("Error: " + e.getMessage()),
        //                () -> System.out.println("Completed"));
        //
        //System.out.println();

        //6
        //merge 2 observables to get operations with intervals in another thread
        var observableOperations =
            Observable.zip(Observable.just(
                    new BigDecimal("100"),
                    new BigDecimal("1000"),
                    new BigDecimal("-200"),
                    new BigDecimal("-300")),
                Observable.interval(1, TimeUnit.SECONDS),
                (operations, intervals) -> operations);

        var disposable = observableOperations
            .doOnDispose(() -> System.out.println("Disposed"))
            .subscribe(s -> System.out.println("Operation: " + s),
                e -> System.out.println("Error: " + e.getMessage()),
                () -> System.out.println("Completed"));
        Thread.sleep(2000);
        disposable.dispose();

        System.out.println();

    }
}
