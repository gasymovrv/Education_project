package examples.algorithms.yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ValidAnagram {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")))) {

            String s1 = reader.readLine();
            String s2 = reader.readLine();
            if (isAnagram(s1, s2)) {
                writer.write("1");
            } else {
                writer.write("0");
            }
        }
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, (oldV, v) -> oldV + v);
        }

        for (int i = 0; i < t.length(); i++) {
            final char tChar = t.charAt(i);
            Integer count = map.get(tChar);
            if (count == null || count == 0) {
                return false;
            }
            map.put(tChar, --count);
        }
        return true;
    }
}
