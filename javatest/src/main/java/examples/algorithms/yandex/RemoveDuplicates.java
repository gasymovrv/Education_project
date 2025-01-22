package examples.algorithms.yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicates {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")))) {

      int n = Integer.parseInt(reader.readLine());
      Set<Integer> uniqueNumbers = new LinkedHashSet<>();
      for (int i = 0; i < n; i++) {
        var num = Integer.parseInt(reader.readLine());
        uniqueNumbers.add(num);
      }

      for (int num : uniqueNumbers) {
        writer.println(num);
      }
    }
  }
}
