package patterns.monad;

public class Main {

    public static void main(String[] args) {
        var obj = new TestClass("name 1");
        Monad.from(obj)
                .map(TestClass::name)
                .map(it -> it.charAt(0))
                .map(it -> {
                    System.out.println(it);
                    return it;
                });
    }

    record TestClass(String name) {

    }
}

