package ru.gasymovrv.kotlintest.patterns

object CompositeApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val square1: Shape = Squuare()
        val square2: Shape = Squuare()
        val triangle1: Shape = Triaangle()

        val square3: Shape = Squuare()
        val circle1: Shape = Cicrle()
        val circle2: Shape = Cicrle()
        val circle3: Shape = Cicrle()

        val composit = Composite()
        val composit1 = Composite()
        val composit2 = Composite()

        composit1.addComponent(square1)
        composit1.addComponent(square2)
        composit1.addComponent(triangle1)

        composit2.addComponent(square3)
        composit2.addComponent(circle1)
        composit2.addComponent(circle2)
        composit2.addComponent(circle3)

        composit.addComponent(composit1)
        composit.addComponent(composit2)
        composit.addComponent(Triaangle())

        //Работаем со всем деревом как с одним объектом
        composit.draw()
    }
}

//-------------------------------Components----------------------------------
//Component
interface Shape {

    //operation
    fun draw()
}

//ConcreteComponent 1
class Squuare : Shape {

    override fun draw() {
        println("Привет, я Квадрат.")
    }
}

//ConcreteComponent 2
class Triaangle : Shape {

    override fun draw() {
        println("Привет, я Треугольник.")
    }
}

//ConcreteComponent 3
class Cicrle : Shape {

    override fun draw() {
        println("Привет, я Круг.")
    }
}

//-------------------------------Composite----------------------------------
class Composite : Shape {

    private val components: MutableList<Shape> = ArrayList()

    fun addComponent(component: Shape) {
        components.add(component)
    }

    fun removeComponent(component: Shape) {
        components.remove(component)
    }

    //operation
    override fun draw() {
        for (component in components) {
            component.draw()
        }
    }
}

