package examples.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least recently used cache.
 * If size is exceeded, the least recently used item is removed.
 */
class LRUCache {
    private final Map<Integer, Integer> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // true sets the map to access order mode,
        // which makes recently accessed items (get or put) appear at the end of the list.
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);

        if (cache.size() > capacity) {
            // Remove the least recently used entry (the first entry in the map)
            Map.Entry<Integer, Integer> firstEntry = cache.entrySet().iterator().next();
            cache.remove(firstEntry.getKey());
        }
    }

    public String toString() {
        return cache.toString();
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.get(2);
        System.out.println(obj);
        obj.put(2, 6);
        System.out.println(obj);
        obj.get(1);
        System.out.println(obj);
        obj.put(1, 5);
        System.out.println(obj);
        obj.put(1, 2);
        System.out.println(obj);
        obj.get(1);
        System.out.println(obj);
        obj.get(2);
        System.out.println(obj);
    }
}
