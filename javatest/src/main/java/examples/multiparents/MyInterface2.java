package examples.multiparents;

public interface MyInterface2 {
    default void print() {
        System.out.println("called print from MyInterface2");
    }
}
