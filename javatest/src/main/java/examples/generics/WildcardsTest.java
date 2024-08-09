package examples.generics;

import examples.generics.entries.Animal;
import examples.generics.entries.Cat;
import examples.generics.entries.HomeCat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Принцип PECS (Producer Extends Consumer Super)
 */
public class WildcardsTest {

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        //printProducer(animalList); //ошибка
        printConsumer(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        catList.add(new HomeCat("123"));
        printProducer(catList);
        printConsumer(catList);

        List<HomeCat> homeCatList = new ArrayList<>();
        homeCatList.add(new HomeCat("homeCat"));
        //homeCatList.add(new Cat()); //ошибка
        //printConsumer(homeCatList); //ошибка
        printProducer(homeCatList);

    }


    /**
     * EXTENDS
     * Producer - тот кто дает нам данные
     * Цель: пройти по готовой заполненной коллекции и сделать что-то с элементами классом ниже Cat
     * <p>
     * Принимаем в ссылку лист с дженериком НИЖЕ по иерархии чем Cat.
     * Мы НЕ можем добавлять ничего в список, т.к. внутри может лежать класс ниже по иерархии
     */
    private static void printProducer(List<? extends Cat> catList) {
        //catList.add(new Object()); //Ошибка
        //catList.add(new Animal()); //Ошибка
        //catList.add(new Cat()) //Ошибка
        //catList.add(new HomeCat("f")); //Ошибка
        //catList.add(new WildCat("fur-fur")); //Ошибка

        List<? extends Cat> catList2 = Arrays.asList(new Cat(), new HomeCat("d"));
        //catList.addAll(catList2); //Ошибка

        Cat cat = catList.get(0);
        System.out.println("Cat from the list:" + cat);

        catList.forEach(System.out::println);
    }

    /**
     * SUPER
     * Consumer - мы
     * Цель: мы должны добавлять в коллекцию любые элементы в рамках иерархии ниже Cat
     * <p>
     * Принимаем в ссылку лист с дженериком ВЫШЕ по иерархии чем Cat.
     * Мы можем добавлять в список элементы наследующиеся от Cat включая сам Cat,
     * т.к. гарантированно придет лист дженериком ВЫШЕ по иерархии чем Cat
     */
    private static void printConsumer(List<? super Cat> catList) {
        //catList.add(new Object());// Ошибка
        //catList.add(new Animal());// Ошибка
        //catList.add(new Animal());
        catList.add(new Cat());
        catList.add(new HomeCat("noName"));


        Object item = catList.get(0);
        System.out.println("item from the list:" + item);

        catList.forEach(System.out::println);
    }

}
