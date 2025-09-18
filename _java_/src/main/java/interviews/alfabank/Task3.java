package interviews.alfabank;

import java.util.Arrays;
import java.util.LinkedList;

//3. Что напишет программа?
public class Task3 {
    public static void main(String[] args) {
        var al = Arrays.asList(1, 2, 3, 4, 5);
        var ll = new LinkedList<>(al);
        ll.add(6);
        al.add(6); //<-UnsupportedOperationException потому что asList возвращает имьутабельный список
        System.out.printf("al.equals(ll) = %s", al.equals(ll));

    }
}
