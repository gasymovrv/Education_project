package examples.java8;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentMapTest {

    public static void main(String[] args) throws InterruptedException {
        var map = new ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Type>>();
        map.put(1, new ConcurrentLinkedQueue<>(List.of(new Type(1, "one-init"))));
        map.put(3, new ConcurrentLinkedQueue<>(List.of(new Type(3, "three-init"))));

        var counter = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {
            CompletableFuture.runAsync(() -> {
                var index = counter.incrementAndGet();
                var list = List.of(
                        new Type(1, String.valueOf(index)),
                        new Type(2, String.valueOf(index)),
                        new Type(3, String.valueOf(index)),
                        new Type(4, String.valueOf(index)),
                        new Type(5, String.valueOf(index)),
                        new Type(6, String.valueOf(index)),
                        new Type(7, String.valueOf(index)),
                        new Type(8, String.valueOf(index)),
                        new Type(9, String.valueOf(index))
                );
                //simple(map, list);
                //merge(map, list);
                compute(map, list);
            });
        }

        Thread.sleep(3000);
        //System.out.println(map);
        System.out.println(map.get(1).size()); // 10001 expected
        System.out.println(map.get(2).size()); // 10000 expected
        System.out.println(map.get(3).size()); // 10001 expected
        System.out.println(map.get(4).size()); // 10000 expected
        System.out.println(map.get(5).size()); // 10000 expected
        System.out.println(map.get(6).size()); // 10000 expected
        System.out.println(map.get(7).size()); // 10000 expected
        System.out.println(map.get(8).size()); // 10000 expected
        System.out.println(map.get(9).size()); // 10000 expected
    }

    // better than merge, because does not create useless objects
    private static void compute(Map<Integer, ConcurrentLinkedQueue<Type>> map, List<Type> list) {
        list.forEach(e -> map.compute(e.id, (key, value) -> {
            if (value == null) {
                emulateLag(); //todo emulation
                return new ConcurrentLinkedQueue<>(List.of(e));
            }
            value.add(e);
            return value;
        }));
        System.out.println("=== Using 'compute' ===");
    }

    // works right, but creates a lot of useless objects
    private static void merge(Map<Integer, ConcurrentLinkedQueue<Type>> map, List<Type> list) {
        list.forEach(e -> {
            ConcurrentLinkedQueue<Type> initial = new ConcurrentLinkedQueue<>();
            initial.add(e);
            map.merge(e.id, initial, (oldVal, newVal) -> {
                oldVal.add(e);
                return oldVal;
            });
        });
        System.out.println("=== Using 'merge' ===");
    }

    // works wrong in concurrent environment
    private static void simple(Map<Integer, ConcurrentLinkedQueue<Type>> map, List<Type> list) {
        list.forEach(e -> {
            final var queue = map.get(e.id);
            if (queue == null) {
                emulateLag(); //todo emulation
                map.put(e.id, new ConcurrentLinkedQueue<>(List.of(e)));
            } else {
                queue.add(e);
            }
        });
        System.out.println("=== Using 'simple' ===");
    }

    private static void emulateLag() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    record Type(
            int id,
            String name
    ) {

    }
}
