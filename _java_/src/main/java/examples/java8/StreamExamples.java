package examples.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamExamples {
    public static void main(String[] args) {
        //{1=[1], 2=[1, 2], 3=[1, 3], 4=[1, 2, 4], 5=[1, 5]}
        System.out.println(method1(6));
        System.out.println(method2(6));
    }

    /**
     * Метод возвращает мапу с ключом - Integer, а значением - List делителей этого числа
     *
     * @param n верхняя граница
     */
    private static Map<Integer, List<Integer>> method1(int n) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    list.add(j);
                }
            }
            map.put(i, list);
        }
        return map;
    }

    /**
     * То же самое что и method1 только на стримах
     *
     * @param n верхняя граница
     */
    private static Map<Integer, List<Integer>> method2(int n) {
        return IntStream.range(1, n).boxed().collect(Collectors.toMap(
                Function.identity(),
                v -> IntStream.range(1, n).boxed().filter(i -> v % i == 0).collect(Collectors.toList()))
        );
    }
}
