package ru.gasymovrv.kotlintest.patterns.state

/**
 * Реализация шаблона состояние 2
 * Переключение состояний внутри самих состояний
 */
object StateApp2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val human = Human()
        human.state = Work()

        for (i in 0..9) {
            human.doSomething()
        }
    }
}

//-------------------------------Context-------------------------------
class Human {

    var state: Activity? = null

    //request
    fun doSomething() {
        state?.doSomething(this)
    }
}

//-------------------------------States-------------------------------
interface Activity {

    //handle
    fun doSomething(human: Human)
}

//ConcreteState 1
class Work : Activity {

    override fun doSomething(context: Human) {
        println("Работаем!!!")
        context.state = WeekEnd()
    }
}

//ConcreteState 2
class WeekEnd : Activity {

    private var count = 0

    override fun doSomething(context: Human) {
        if (count < 3) {
            println("Отдыхаем (Zzz)")
            count++
        } else {
            context.state = Work()
        }
    }
}
