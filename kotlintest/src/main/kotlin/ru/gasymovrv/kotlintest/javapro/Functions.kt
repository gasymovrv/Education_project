package ru.gasymovrv.kotlintest.javapro

fun main() {
    println(
        "util func, call Java method:${
            JavaClass().method()
        }"
    )

    println("-".repeat(30))

    val clos = closur()
    println(clos(1))
    println(clos(2))
    println(clos(7))
    println(clos(7))

    println("-".repeat(30))

    val lClos = lambdaClosur()
    println(lClos(1))
    println(lClos(2))
    println(lClos(7))
    println(lClos(7))

    println("-".repeat(30))

    foo0()
    foo1()
}

fun ifElse(arg: Int): String {
    if (arg == 1) {
        return "one"
    } else if (arg == 2) {
        return "two"
    }
    return when (arg) {
        3 -> "three"
        4 -> "four"
        5 -> "five"
        else -> "other"
    }
}

fun getNullableClass(str: String?): KotlinClass? {
    return if (str == null) {
        null
    } else {
        KotlinClass(str)
    }
}

fun closur(): (v: Int) -> Int { //(v: Int) -> Int -функциональный тип, т.е. функция которая принимает v:Int и возвращает Int
    var count = 0
    return fun(v): Int {
        count += v
        return count
    }
}

fun lambdaClosur(): (v: Int) -> Int { //(v: Int) -> Int -функциональный тип, т.е. функция которая принимает v:Int и возвращает Int
    var count = 0
     return {
        count += it
        count
    }
}

fun foo0() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) {
            println()
            return // нелокальный возврат, непосредственно к объекту вызывающему функцию foo()
        }
        print(it)
    }
    println("эта строка не достижима")
}

fun foo1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // локальный возврат внутри лямбды, то есть к циклу forEach
        print(it)
    }
    println(" выполнится с использованием неявной метки(forEach@)")
}