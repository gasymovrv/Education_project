package examples.multithreading.racecondition;

public class LazyInitRace {
    public static int instanceCount = 0;

    private static LazyInitRace instance = null;

    private final int instanceNumber;

    /**
     * Замедляем время создания объекта для демонстрации состояний гонки
     */
    private LazyInitRace() {
        instanceCount++;
        instanceNumber = instanceCount;
        for (int i = 0; i < 100000; i++) {
            i++;
        }
    }

    /**
     * Потоко НЕбезопасный метод - при параллельном доступе есть вероятность создать несколько экземпляров
     */
    public static LazyInitRace getInstance() {
        if (instance == null)
            instance = new LazyInitRace();
        return instance;
    }

    @Override
    public String toString() {
        return "LazyInitRace{" +
                "instanceNumber=" + instanceNumber +
                '}';
    }
}
