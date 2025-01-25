package ru.gasymovrv.kotlintest.coursera.functional

import ru.gasymovrv.kotlintest.coursera.Words
import ru.gasymovrv.kotlintest.coursera.extensions.eq
import ru.gasymovrv.kotlintest.coursera.oop.Contact
import java.io.BufferedReader
import java.io.FileReader
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

data class Person(val name: String, val age: Int) {
    var children: MutableList<Person> = mutableListOf()

    fun doSomething() = println("do something from Person: $this")
}

fun main() {
    val sasha = Person("Sasha", 45)
    val petr = Person("Petr", 35)
    val oleg = Person("Oleg", 35)
    val nika = Person("Nika", 25)
    val anna = Person("Anna", 27)

    val list = listOf(sasha, petr, oleg)

    // ------------------------- collection functions ------------------------------------
    print("count: ")
    println(list.count { it.age == 35 })

    print("maxOf: ")
    println(list.maxOf { it.age }) //exceptionable

    print("maxOfOrNull: ")
    println(list.maxOfOrNull { it.age }) //nullable

    print("maxByOrNull: ")
    println(list.maxByOrNull { it.age }) //if all are equal takes the first

    print("any: ")
    println(list.any { it.age == 45 })

    print("all: ")
    println(list.all { it.age <= 45 })

    print("none: ")
    println(list.none { it.age > 45 })

    print("find: ")
    println(list.find { it.age == 45 }) //nullable

    print("firstOrNull: ")
    println(list.firstOrNull { it.age == 65 }) //=find

    print("first: ")
    println(list.first())

    print("first(prediction): ")
    println(list.first { it.age == 35 }) //exceptionable

    print("last: ")
    println(list.last())

    print("sortedBy: ")
    println(listOf(sasha, petr, oleg, nika, anna).sortedBy { it.age })

    print("sortedByDescending: ")
    println(listOf(sasha, petr, oleg, nika, anna).sortedByDescending { it.age })

    print("mapNotNull: ")
    println(listOf(sasha, petr, null, nika, null).mapNotNull { it?.age })

    print("groupBy: ")
    println(list.groupBy { it.age })

    print("groupingBy + aggregate: ")
    println(list.groupingBy { it.age }
        .aggregate { key, accumulator: StringBuilder?, element, first ->
            if (first) // first found element with 'key'
                StringBuilder(element.name)
            else
                accumulator!!.append("+").append(element.name)
        })

    print("associateBy: ")
    println(list.associateBy { it.age }) //unique keys, duplicates will be replaced

    print("associate: ")
    println(list.associate { it.age to it.name }) //unique keys, duplicates will be replaced

    val list2 = listOf(nika, anna)

    print("zip: ")
    println(list.zip(list2).joinToString("; ", "[", "]"))

    print("zipWithNext: ")
    println(list.zipWithNext().joinToString("; ", "[", "]"))

    val list3 = listOf(list, list2)

    print("flatten: ")
    println(list3.flatten())

    sasha.children.add(nika)
    sasha.children.add(oleg)
    petr.children.add(anna)
    print("flatMap: ")
    println(list.flatMap { it.children })

    print("partition: ")
    val (olderThan30, others) = listOf(list, list2)
        .flatten()
        .partition { it.age > 30 }//returns Pair<List, List>, save in destructuring declaration
    println("older than 30: $olderThan30; others: $others")

    print("getOrElse: ")
    println(list.groupBy { it.age }.getOrElse(0) { listOf(Person("unknown", 0)) })

    examplesOfWorkWithMap(list)

    print("eachCount: ")
    println(list.groupingBy { it.age }.eachCount())

    val map = list.associateBy { it.age }
    print("mapValues: ")
    println(map.mapValues { Person(it.value.name + it.key, it.key) })

    //-------------------------------- Lambda types ----------------------------------------------
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val isEven: (Int) -> Boolean = { i: Int -> i % 2 == 0 }
    val isEvenWithReceiver: Int.() -> Boolean = { this % 2 == 0 }
    println(sum(1, 2))
    println(isEven(2))
    println(2.isEvenWithReceiver())
    println({ isEven(sum(3, 6)) }()) //creates lambda and invokes it
    println(run { isEven(sum(3, 6)) }) //creates lambda and invokes it

    val runnable: Runnable = Runnable { println("runnable!") } //Functional interfaces from Java
    runnable.run()

    var myPrint: ((any: Any) -> Unit)? = null
    myPrint?.invoke("something") //Call nullable lambda
    myPrint = { println("My print: $it") }
    myPrint.invoke("something") //Call nullable lambda

    val f1: KFunction0<Unit> = ::doSomething//Save function to variable
    val f2: KFunction1<Person, Unit> = Person::doSomething//non-bound reference
    val f3: (Person) -> Unit = Person::doSomething//non-bound reference
    val f4: () -> Unit = oleg::doSomething//bound reference
    f1()
    f2(oleg)
    f3(oleg)
    f4()

    //----------------------- return from lambda----------------------------------------

    val list4 = listOf(list, list2).flatten()
    fun wrongReturn(list: List<Person>): List<Person> {
        return list.flatMap {
            if (it.age > 30) return listOf(it) //return from parent function
            listOf(it, it)
        }
    }
    println(wrongReturn(list4))

    fun duplicateYoungest(list: List<Person>): List<Person> {
        return list.flatMap {
            if (it.age > 30) return@flatMap listOf(it) //can be @duplicateYoungest or @main
            listOf(it, it)
        }
    }
    println(duplicateYoungest(list4))

    fun duplicateYoungestLabeled(list: List<Person>): List<Person> {
        return list.flatMap label@{
            if (it.age > 30) return@label listOf(it)
            listOf(it, it)
        }
    }
    println(duplicateYoungestLabeled(list4))

    fun duplicateYoungestAnonymousFun(list: List<Person>): List<Person> {
        return list.flatMap(fun(v): List<Person> {
            if (v.age > 30) return listOf(v)
            return listOf(v, v)
        })
    }
    println(duplicateYoungestAnonymousFun(list4))

    //-------------------- scope functions - let, takeIf, with and etc. ------------------------------
    val number = 42

    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    println("takeIf $number is even: $evenOrNull, takeUnless $number is even: $oddOrNull")

    repeat(2) {
        println("from repeat, iteration: $it")
    }

    //.use - instead of try-with-resources
    val firstLine = BufferedReader(FileReader("readme.md")).use { br -> br.readLine() }
    println(firstLine)

    //.with is a high order fun with arguments: receiver, lambda with receiver.
    // Returns result of lambda
    val sb = StringBuilder()
    val s1 = with(sb) {
        appendLine("Alphabet")
        for (c in 'a'..'z') {
            append(c)
        }
    }
    println(s1)

    val resultFromWith = with(oleg) {
        println("with(oleg): name=$name, age=$age")
        return@with "result from with"
    }
    println(resultFromWith)

    val words = Words()
    exampleOfWithAndPrivateExtensions(words)

    //.run is an extension fun with argument: lambda with receiver. Returns result of lambda
    val nullablePerson: Person? = Person("Nullable-person", 0)
    nullablePerson?.run {
        println("nullablePerson?.run: name=$name, age=$age")
    }

    //.let is an extension fun with simple lambda argument. Returns result of lambda
    getContact()?.let { sendPostTo(it) }
    number.takeIf { it < 40 }?.let { println("took a number < 40: $it") }
    val resultFromLet = number.takeIf { it > 40 }?.let {
        println("took a number > 40: $it"); return@let true
    }
    println("result from let: $resultFromLet")

    //.apply is an extension fun with argument: lambda with receiver. Returns this
    applyExample(nullablePerson)

    //.also is like .let but .also returns it
    val personFromAlso = nullablePerson?.also { applyExample(it) }

    val s2 = buildString {
        appendLine("Alphabet")
        for (c in 'a'..'z') {
            append(c)
        }
    }
    println(s2)

    //.withLock - instead of synchronized
}

fun doSomething() = println("do something")

fun sendPostTo(contact: Contact) {
    println("contact through let: $contact")
}

fun getContact(): Contact? = Contact("name", "addr")

fun examplesOfWorkWithMap(list: List<Person>) {
    val map1 = mutableMapOf<Int, MutableList<Person>>()
    //Instead of it we can use simple .groupBy (but result is immutable) or .merge or .getOrPut
    for (person in list) {
        if (person.age !in map1) {
            map1[person.age] = mutableListOf()
        }
        map1.getValue(person.age) += person
    }
    println("update values of map in old style, result: $map1")

    val map2 = list.groupBy { it.age }
    println("get map from list.groupBy, result: $map2")

    val map3 = mutableMapOf<Int, MutableList<Person>>()
    for (person in list) {
        val personList = map3.getOrPut(person.age) { mutableListOf() }
        personList += person
    }
    println("map.getOrPut, result: $map3")

    val map4 = mutableMapOf<Int, MutableList<Person>>()
    list.forEach { person ->
        map4.merge(person.age, mutableListOf(person)) { oldValue, _ ->
            oldValue += person
            return@merge oldValue
        }
    }
    println("map.merge, result: $map4")
}

fun applyExample(nullablePerson: Person?) {
    val p = nullablePerson?.apply {
        println("nullablePerson?.run: name=$name, age=$age")
    } ?: return
    println("apply gave non-null person: $p")
}

fun exampleOfWithAndPrivateExtensions(words: Words) {
    with(words) {
        // The following two lines should compile:
        "one".record()
        +"two"
    }
    words.toString() eq "[one, two]"
}

