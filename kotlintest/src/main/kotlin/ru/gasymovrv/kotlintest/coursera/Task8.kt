package ru.gasymovrv.kotlintest.coursera

// Complete the declaration of the class A without throwing NullPointerException explicitly
// so that NPE was thrown during the creation of its subclass B instance.
open class A1(open val value: String) {
    init {
        println(value.length)
    }
}

class B(override val value: String) : A1(value)

fun main(args: Array<String>) {
    B("a")
}
