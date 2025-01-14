package ru.gasymovrv.kotlintest.patterns

object DelegateApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val painter = Painter()

        painter.graphics = Square()
        painter.draw()

        painter.graphics = Triangle()
        painter.draw()
    }
}


interface Graphics {

    fun draw()
}

class Triangle : Graphics {

    override fun draw() {
        println("Рисуем треугольник")
    }
}

class Square : Graphics {

    override fun draw() {
        println("Рисуем квадрат")
    }
}

//Delegate
class Painter {

    var graphics: Graphics? = null

    fun draw() {
        graphics!!.draw()
    }
}
