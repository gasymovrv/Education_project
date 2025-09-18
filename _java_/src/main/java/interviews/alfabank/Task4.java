package interviews.alfabank;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;

public class Task4 {
    public static void main(String[] args) {
        Random rand = new Random();
        var list = new ArrayList<byte[]>(10);
        // Вставка 10 элементов каждый по 1 мб
        for (int i = 0; i < 10; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(1 << 20);   // 1 << 20 - 1 Mb
            rand.nextBytes(buffer.array());
            list.add(buffer.array());
        }
        //превышаем capacity, сколько байт будет вынужден копировать процессор?
        ByteBuffer buffer = ByteBuffer.allocate(1 << 20);
        rand.nextBytes(buffer.array());
        list.add(buffer.array());
    }
}

// Правильный ответ:
// Только ссылки в массиве ArrayList будут скопированы: 10 элементов.
// В байтах: 10 * sizeOf(ref)
// с CompressedOops: ≈ 40 Б (10 × 4 Б);
// без него: ≈ 80 Б (10 × 8 Б).
// Сами массивы по 1 МиБ не копируются.
