package examples.java8;


public class ClassWithLambdaMethod {
    public static <T> void handle(FieldHandler<T> fh){
        T t = fh.action();
        System.out.printf("печатаем значение из геттера: %s\n",t);
    }
}
