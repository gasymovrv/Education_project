package examples.multithreading;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import examples.utils.TimeMeasurementUtil;

public class ParallelStreamTest {

    public static void main(String[] args) throws Exception {
        var list = List.of("001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012");
        threadSleepInStream(list.stream());
        threadSleepInParallelStream(list.stream());
    }

    private static void threadSleepInParallelStream(Stream<String> stream) {
        TimeMeasurementUtil.measureTimeWithIterationsAndDescription(
                "Thread.sleep in parallel stream", 1, () -> doSomething(stream.parallel()));
    }

    private static void threadSleepInStream(Stream<String> stream) {
        TimeMeasurementUtil.measureTimeWithIterationsAndDescription(
                "Thread.sleep in stream", 1, () -> doSomething(stream));
    }

    private static void doSomething(Stream<String> stream) {
        var handledStream = stream.map((el) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return el.replaceAll("^0+", "");
        }).collect(Collectors.toList());
        System.out.println(handledStream);
    }
}
