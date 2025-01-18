package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MainTest {

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        final String s = br.readLine();
        if (s.equals("exit")) {
          break;
        }
        var a = Integer.parseInt(s);
        var b = Integer.parseInt(br.readLine());
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(a & b);
      }
    }
  }
}
