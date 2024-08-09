package examples.collections;

import static examples.utils.TimeMeasurementUtil.measureTime;

import examples.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformanceTest extends Test {
    private static Test thisOne = new ListPerformanceTest();

    public static void main(String[] args) {
        thisOne.go();
    }

    @Override
    public void contentGo() {
        int million1 = 1000000;
        int million2 = 2000000;
        int thousand10 = 10000;
        int thousand100 = 100000;
        int thousand50 = 50000;
        int thousand25 = 25000;

        System.out.format("ArrayList, get(size/2), operations/size=%d, time:%d ms\n", million1, testGet(new ArrayList<>(), million1));
        System.out.format("LinkedList, get(size/2), operations/size=%d, time:%d ms\n", thousand25, testGet(new LinkedList<>(), thousand25));

        System.out.println();
        System.out.format("ArrayList, add(T), operations=%d, time:%d ms\n", million2, testAdd(new ArrayList<>(), million2));
        System.out.format("LinkedList, add(T), operations=%d, time:%d ms\n", million2, testAdd(new LinkedList<>(), million2));

        System.out.println();
        System.out.format("ArrayList, add(0, T), operations/size=%d, time:%d ms\n", thousand100, testInsertFirst(new ArrayList<>(), thousand100));
        System.out.format("LinkedList, add(0, T), operations/size=%d, time:%d ms\n", thousand100, testInsertFirst(new LinkedList<>(), thousand100));

        System.out.println();
        System.out.format("ArrayList, add(size/2, T), operations/size=%d, time:%d ms\n", thousand25, testInsertInMiddle(new ArrayList<>(), thousand25));
        System.out.format("LinkedList, add(size/2, T), operations/size=%d, time:%d ms\n", thousand25, testInsertInMiddle(new LinkedList<>(), thousand25));

        System.out.println();
        System.out.format("ArrayList, add(size/5, T), operations/size=%d, time:%d ms\n", thousand25, testInsertInBeginning(new ArrayList<>(), thousand25));
        System.out.format("LinkedList, add(size/5, T), operations/size=%d, time:%d ms\n", thousand25, testInsertInBeginning(new LinkedList<>(), thousand25));

        System.out.println();
        System.out.println("Multi test:");

        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.format("\tLinkedList, add(0, T), operations/size=%d, time:%d ms\n", million1, testInsertFirst(linkedList, million1));

        ArrayList<Integer> arrayList = new ArrayList<>();
        long copyTime = measureTime(() -> arrayList.addAll(linkedList));
        System.out.format("\tCopy from LinkedList to ArrayList, 1 copy operation of size=%d, time:%d ms\n", linkedList.size(), copyTime);

        System.out.format("\tArrayList, get(size/2), operations/size=%d, time:%d ms\n", arrayList.size(), testGet(arrayList, arrayList.size()));
    }

    private long testGet(List<Integer> list, int operations) {
        prepareList(list, operations);

        return measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                list.get(list.size() / 2);
            }
        });
    }

    private long testAdd(List<Integer> list, int operations) {
        return measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                list.add(i);
            }
        });
    }

    private long testInsertFirst(List<Integer> list, int operations) {
        prepareList(list, operations);

        return measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                list.add(0, i);
            }
        });
    }

    private long testInsertInMiddle(List<Integer> list, int operations) {
        prepareList(list, operations);

        return measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                list.add(list.size() / 2, i);
            }
        });
    }

    private long testInsertInBeginning(List<Integer> list, int operations) {
        prepareList(list, operations);

        return measureTime(() -> {
            for (int i = 0; i < operations; i++) {
                list.add(list.size() / 5, i);
            }
        });
    }

    private void prepareList(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
}
