package interviews;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LuxoftRussianPost {
    public static void main(String[] args) {
        System.out.println(join(Arrays.asList("a", "bC7", null, "d")));  //expected "A,BC7,D"
    }

    /**
     * Метод по конкатенации списка строк в одну
     * Итератор лучше использовать, чтобы ускориться если нам передадут LinkedList
     * Если мы будем делать обход через list.get(i), то для LinkedList в цикле получим O(n^2)
     *
     * @param list список строк
     * @return строки, сконкатенированные в одну через запятую
     */
    public static String join(List<String> list) {
        if (list == null) {
            throw new RuntimeException("Argument 'list' at method 'join' cannot be null");
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            //Тут надо сказать что зависит от использования, может лучше выбросить исключение
            if (s == null) {
                continue;
            }
            sb.append(s.toUpperCase());
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
