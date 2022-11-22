package examples.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeMeasurementUtil {

    /**
     * Измеритель времени выполнения функции {@code function}
     *
     * @param function функция для замера времени ее выполнения
     * @return время в миллисекундах
     */
    public static long measureTime(Runnable function) {
        long l1 = System.currentTimeMillis();
        function.run();
        long l2 = System.currentTimeMillis();
        return l2 - l1;
    }

    /**
     * То же что и measureTime, но результат в наносекундах
     *
     * @return время в наносекундах
     */
    public static long measureNanoTime(Runnable function) {
        long l1 = System.nanoTime();
        function.run();
        long l2 = System.nanoTime();
        return l2 - l1;
    }

    /**
     * Измеряет время выполнения функции {@param func} повторяя ее {@param iterations} раз
     * и добавляя описание
     *
     * @param topic      заголовок или тема
     * @param iterations количество повторений
     * @param func       измеряемая функция
     */
    public static void measureTimeWithIterationsAndDescription(String topic, int iterations, Runnable func) {
        System.out.println(topic);
        long result = TimeMeasurementUtil.measureTime(() -> {
            for (int i = 1; i <= iterations; i++) {
                System.out.printf("Iteration #%d: %dms\n",
                        i,
                        TimeMeasurementUtil.measureTime(func));
            }
        });
        System.out.printf("Total time: %dms\n", result);
    }
}
