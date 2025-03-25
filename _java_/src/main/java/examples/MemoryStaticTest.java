package examples;

import java.util.ArrayList;
import java.util.List;

public class MemoryStaticTest {
    public static List<Double> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Debug Point 1");
        new MemoryStaticTest().populateList();
        System.out.println("Debug Point 3");
    }

    public void populateList() {
        for (int i = 0; i < 100000000; i++) {
            list.add(Math.random());
        }
        System.out.println("Debug Point 2");
    }
}
