package examples.collections;

import java.util.Arrays;

public class MyArrayList implements MyList {

    private Object[] array = new Object[3];

    private int lastIndex = 0;

    public void add(Object o) {
        array[lastIndex] = o;
        lastIndex++;
        if (lastIndex >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    public Object get(int index) {
        return array[index];

    }

    public int size() {
        return lastIndex;
    }

}
