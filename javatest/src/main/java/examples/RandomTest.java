package examples;

import java.util.Random;
/**
 * Методы генерации в определенных диапазонах
 */
public class RandomTest extends Test {
    private static Test thisOne = new RandomTest();

    @Override
    public void contentGo(){
        System.out.println("От 0 до 100 - целые");
        for (int i = 0; i < 20; i++) {
            System.out.println(getRandomIntegerInRange(0, 100));
        }
        System.out.println("От 0 до 10 - вещественные");
        for (int i = 0; i < 20; i++) {
            System.out.println(getRandomDoubleInRange(0, 10));
        }
    }

    /**
     * @param min от (включительно)
     * @param max по (включительно)
     * @return рандомное int число от min по max
     */
    private static int getRandomIntegerInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * @param min от (включительно)
     * @param max до (НЕ включительно)
     * @return рандомное double число от min до max
     */
    private static double getRandomDoubleInRange(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return (Math.random() * (max - min)) + min;
    }

    public static void main(String[] args) {
        thisOne.go();
    }
}
