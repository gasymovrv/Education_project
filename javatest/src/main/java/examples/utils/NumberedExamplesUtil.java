package examples.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberedExamplesUtil {

    /**
     * Печатает нумерацию с комментариями по шаблону:
     * --- {@code num}: {@code comments[0]}; {@code comments[1]}; {@code comments[n]} ---
     *
     * @param num      номер примера
     * @param comments комментарии или описание
     */
    public static void printNumberOfExample(long num, String... comments) {
        System.out.println();
        List<String> commentsList = Arrays.asList(comments);
        if (commentsList.isEmpty()) {
            System.out.printf("--- %d ---\n", num);
        } else {
            StringBuilder sb = new StringBuilder();
            AtomicInteger counter = new AtomicInteger(0);
            commentsList.forEach(str -> {
                sb.append(str);
                if (counter.getAndIncrement() != commentsList.size() - 1) {
                    sb.append("; ");
                }
            });
            System.out.printf("--- %d: %s ---\n", num, sb.toString());
        }
    }
}
