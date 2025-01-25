package examples.collections.map;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Моя реализация HashMap для доступа к массиву корзин через рефлексию
 */
public class MyHashMap<V, K> extends HashMap<V, K> {


    public MyHashMap() {
        super();
    }

    public MyHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public MyHashMap(Map<? extends V, ? extends K> m) {
        super(m);
    }

    /**
     * Вложенный класс Node, используемый в HashMap для хранения key/value
     */
    private Class<?> nodeClass;

    {
        //Ищем рефлексией класс Node и записываем его в наше поле nodeClass
        for (Class<?> c : this.getClass().getSuperclass().getDeclaredClasses()) {
            if (c.getSimpleName().equals("Node")) {
                nodeClass = c;
            }
        }
    }

    /**
     * Получить через рефлексию массив (поле table), хранящий корзины из HashMap
     */
    private Object[] getTable() {
        Field field;
        try {
            Class<?> hashMapClass = this.getClass().getSuperclass();

            field = hashMapClass.getDeclaredField("table");
            field.setAccessible(true);
            return (Object[]) field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получаем через рефлексию поле hash из класса Node
     */
    private Object getHash(Object node) {
        return getNodeFieldByName(node, "hash");
    }

    /**
     * Получаем через рефлексию поле key из класса Node
     */
    private Object getKey(Object node) {
        return getNodeFieldByName(node, "key");
    }

    /**
     * Получаем через рефлексию поле value из класса Node
     */
    private Object getValue(Object node) {
        return getNodeFieldByName(node, "value");
    }

    /**
     * Получаем через рефлексию поле next из класса Node
     */
    private Object getNext(Object node) {
        return getNodeFieldByName(node, "next");
    }


    private Object getNodeFieldByName(Object node, String fieldName) {
        Field field;
        try {
            field = nodeClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(node);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Преобразуем в строку все элементы массива table
     */
    @Override
    public String toString() {
        Object[] table = getTable();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Object n : table) {
            sb.append(counter++).append("\t").append(nodeToString(n)).append("\n");
        }
        return sb.toString();
    }

    private String nodeToString(Object node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append("firstNode{hash=").append(getHash(node))
                    .append(", key=").append(getKey(node)).
                    append(", value=").append(getValue(node));
            Object temp = getNext(node);
            while (temp != null) {
                sb.append(", next{hash=").append(getHash(temp))
                        .append(", key=").append(getKey(temp))
                        .append(", value=").append(getValue(temp))
                        .append("}");
                temp = getNext(temp);
            }
            sb.append("}");
        }
        return sb.toString();
    }
}
