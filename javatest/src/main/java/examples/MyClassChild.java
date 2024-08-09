package examples;

public class MyClassChild extends MyClass{
    public static void printSomething(String s){
        System.out.println("printSomething from child="+s);
    }

    public MyClassChild(Long id, String info) {
        super(id, info);
    }
}
