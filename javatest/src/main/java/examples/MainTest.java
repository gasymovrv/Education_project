package examples;

import java.io.IOException;
import java.util.LinkedList;

class MainTest {

  public static void main(String[] args) throws IOException {
    var list = new LinkedList<Integer>();

    for (int i = 0; i < 1000; i++) {
      list.add(i);
    }

    final var iterator = list.listIterator();
    while(iterator.nextIndex() < 500) {
      iterator.next();
    }
    iterator.add(1001);

    System.out.println(list);
  }
}
