package examples;

import static examples.utils.NumberedExamplesUtil.printNumberOfExample;

import examples.java8.ClassWithLambdaMethod;
import examples.java8.MyKeyExtractor;
import examples.java8.Pojo;
import examples.java8.PojoCreator;
import examples.java8.StringGetter;
import examples.java8.StringInputFunc;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Java8Test extends Test {
    private static Test thisOne = new Java8Test();

    @Override
    public void contentGo() {
        Pojo pojo = new Pojo(1L, "Pojo object from variable 'pojo'");

        //Функциональные интерфейсы
        printNumberOfExample(1, "Ссылка на не статический метод");
        StringGetter stringGetter = pojo::getInfo; //не статический
        pojo.setInfo("new");
        System.out.println(stringGetter.get());

        printNumberOfExample(2, "Ссылка на статический метод");
        StringInputFunc stringInputFunc = MyClass::printSomething; //статический
        stringInputFunc.func("static method from MyClass");

        printNumberOfExample(3, "Ссылка на конструктор");
        PojoCreator pojoCreator = Pojo::new; //конструктор
        pojoCreator.func(2L,"gjkl");
        pojo= pojoCreator.func(3L,"gjkl2");
        System.out.println(pojo);
        System.out.println(pojo.getId());

        printNumberOfExample(4, "Comparator.comparing(Pojo::getInfo).reversed()");
        List<Pojo> pojos = new ArrayList<>();
        pojos.add(new Pojo(4L,"C"));
        pojos.add(new Pojo(5L,"B"));
        pojos.add(new Pojo(6L,"A"));
        pojos.add(new Pojo(7L,"D"));
        pojos.add(new Pojo(8L,"D"));
        pojos.sort(Comparator.comparing(Pojo::getInfo).reversed());
        System.out.println(pojos);

        printNumberOfExample(5, "Свой функ-ый интерфейс с дженериком для геттеров");
        ClassWithLambdaMethod.handle(new Pojo(9L, "Pojo 9")::getInfo);

        printNumberOfExample(6, "Свой функ-ый интерфейс с дженериком для геттеров (странная херня где добавляется аргумент)");
        MyKeyExtractor.printKey(Pojo::getInfo, new Pojo(10L, "pojo10"));
    }

    public static void main(String[] args) {
        thisOne.go();
    }
}
