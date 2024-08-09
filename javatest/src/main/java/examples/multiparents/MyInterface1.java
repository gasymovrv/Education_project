package examples.multiparents;

public interface MyInterface1 {
    default void print(){
        System.out.println("called print from MyInterface1");
    }
}
