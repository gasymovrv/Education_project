package interviews.alfabank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//2. написать код, чтобы найти чего нехватает, что лишнее и что поменялось
public class Task2 {
    public static void main(String[] args) {
        var old = Map.of("Коля", 1, "Петя", 5, "Таня", 4);
        var fresh = Map.of("Коля", 3, "Вика", 7, "Таня", 4, "Ваня", 9);

        var added = new HashMap<String, Integer>();
        var updated = new HashMap<String, Integer>();
        var removed = new HashSet<>(old.keySet());

        for (var entry : fresh.entrySet()) {
            var oldValue = old.get(entry.getKey());
            if (oldValue == null) {
                added.put(entry.getKey(), entry.getValue());
            } else if (!entry.getValue().equals(oldValue)) {
                updated.put(entry.getKey(), entry.getValue());
            }
            removed.remove(entry.getKey());
        }

        System.out.println("Added: " + added);
        System.out.println("Updated: " + updated);
        System.out.println("Removed: " + removed);
    }
}
