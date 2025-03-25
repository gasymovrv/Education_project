package coursera.oop.generics

import coursera.oop.generics.entries.Animal
import coursera.oop.generics.entries.Cat
import coursera.oop.generics.entries.HomeCat
import java.util.*

fun main(args: Array<String>) {

    val animalList: MutableList<Animal> = ArrayList()
    animalList.add(Animal())
    //printProducer(animalList); //ошибка
    printConsumer(animalList)

    val catList: MutableList<Cat> = ArrayList()
    catList.add(Cat())
    catList.add(HomeCat("123"))
    printProducer(catList)
    printConsumer(catList)

    val homeCatList: MutableList<HomeCat> = ArrayList()
    homeCatList.add(HomeCat("homeCat"))
    //homeCatList.add(Cat()); //ошибка
    //printConsumer(homeCatList); //ошибка
    printProducer(homeCatList)
}

/**
 * EXTENDS / OUT
 * Producer - тот кто дает нам данные
 * Цель: пройти по готовой заполненной коллекции и сделать что-то с элементами классом ниже Cat
 *
 *
 * Принимаем в ссылку лист с дженериком НИЖЕ по иерархии чем Cat.
 * Мы НЕ можем добавлять ничего в список, т.к. внутри может лежать класс ниже по иерархии
 */
private fun printProducer(catList: MutableList<out Cat>) {
    //catList.add(Any()); //Ошибка
    //catList.add(Animal()); //Ошибка
    //catList.add(Cat()) //Ошибка
    //catList.add(HomeCat("f")); //Ошибка
    //catList.add(new WildCat("fur-fur")); //Ошибка
    val catList2 = Arrays.asList(Cat(), HomeCat("d"))
    //catList.addAll(catList2); //Ошибка
    val cat = catList[0]
    println("Cat from the list:$cat")
    catList.forEach { x: Any? -> println(x) }
}

/**
 * SUPER / IN
 * Consumer - мы
 * Цель: мы должны добавлять в коллекцию любые элементы в рамках иерархии ниже Cat
 *
 *
 * Принимаем в ссылку лист с дженериком ВЫШЕ по иерархии чем Cat.
 * Мы можем добавлять в список элементы наследующиеся от Cat включая сам Cat,
 * т.к. гарантированно придет лист дженериком ВЫШЕ по иерархии чем Cat
 */
private fun printConsumer(catList: MutableList<in Cat>) {
    //catList.add(new Object());// Ошибка
    //catList.add(new Animal());// Ошибка
    //catList.add(new Animal());
    catList.add(Cat())
    catList.add(HomeCat("noName"))
    val item = catList[0]!!
    println("item from the list:$item")
    catList.forEach { x: Any? -> println(x) }
}
