package ru.gasymovrv.kotlintest.coursera.functional

fun main() {
    //brings performance overhead (object for lambda)
    myRun { println("Hi") }
    //in comparison to:
    println("Hi")


    //NO performance overhead, because 'run' is inlined
    run { println("Hi") }
    //in comparison to:
    println("Hi")

    //Применение inline оправдано:
    for (i in 0..100) {
        myInlinedRun { println("myInlinedRun $i!") }
    }
}

fun myRun(lambda: () -> Unit) {
    lambda()
}

//Лучше всего делать inline когда аргумент - лямбда и когда например вызываем эту функцию в цикле,
//т.к. будет создано множество объектов для лямбд
inline fun myInlinedRun(lambda: () -> Unit) {
    lambda()
}

//StringBuilder.() -> Unit - lambda with receiver. It's like extension function
inline fun buildString(builderAction: StringBuilder.() -> Unit): String {
    println("my custom buildString!")
    val stringBuilder = StringBuilder()
    stringBuilder.builderAction()
    return stringBuilder.toString()
}

//Пример ограничений inline
inline fun myInlinedRun2(noinline lambda: () -> Unit, lambda2: () -> Unit) {
    regularFun(lambda)
    //Нельзя передавать inline лямбду в noinline метод, т.к. она встраивается в месте вызова
    //regularFun(lambda2)
}

fun regularFun(lambda: () -> Unit) {
    lambda()
}
