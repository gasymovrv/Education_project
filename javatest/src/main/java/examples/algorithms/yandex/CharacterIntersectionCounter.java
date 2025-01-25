package examples.algorithms.yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CharacterIntersectionCounter {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final String jewelsStr = br.readLine();
            final String stonesStr = br.readLine();

            Set<Character> jewels = new HashSet<>();
            for (char c : jewelsStr.toCharArray()) {
                jewels.add(c);
            }
            int count = 0;
            for (char c : stonesStr.toCharArray()) {
                if (jewels.contains(c)) {
                    count++;
                }
            }

            System.out.println(count);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
