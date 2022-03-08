package ru.gasymovrv.kotlintest.javapro.sealed

fun main() {
    println(eval(Const(235.1)))
    println(eval(Sum(Const(235.1), Const(566.632))))
    println(eval(NotNumber))

    val result: Optional<Double> = randomValue()

    println("result.isPresent() = ${result.isPresent()}")
    if(result is Some) {
        println("result.value = ${result.value}")
    }
}
