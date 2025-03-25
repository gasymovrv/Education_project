package patterns.flyweight

import java.util.*

object FlyweightApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val shapeFactory = ShapeFactory()

        val shapes: MutableList<Shape> = ArrayList()

        shapes.add(shapeFactory.getShape("квадрат"))
        shapes.add(shapeFactory.getShape("круг"))
        shapes.add(shapeFactory.getShape("круг"))
        shapes.add(shapeFactory.getShape("точка"))
        shapes.add(shapeFactory.getShape("квадрат"))
        shapes.add(shapeFactory.getShape("круг"))

        val rand = Random()
        for (shape in shapes) {
            val x = rand.nextInt(100)
            val y = rand.nextInt(100)
            print("Объект(хэш): " + shape.javaClass.simpleName + "(" + shape.hashCode() + ")\t\tdraw: ")
            shape.draw(x, y)
        }
    }
}

//-------------------------------Flyweights----------------------------------
//Flyweight
interface Shape {

    //operation
    fun draw(x: Int, y: Int)
}

//ConcreteFlyweight 1
class Point : Shape {

    override fun draw(x: Int, y: Int) {
        println("($x,$y): рисуем точку ")
    }
}

//ConcreteFlyweight 2
class Circle : Shape {

    private val r = 5
    override fun draw(x: Int, y: Int) {
        println("($x,$y): рисуем круг радиусом $r")
    }
}

//ConcreteFlyweight 3
class Square : Shape {

    private val a = 10
    override fun draw(x: Int, y: Int) {
        println("($x,$y): рисуем квадрат со стороной $a")
    }
}

//-------------------------------FlyweightFactory----------------------------------
class ShapeFactory {

    //getFlyweight(in key)
    fun getShape(shapeName: String): Shape {
        var shape = shapes[shapeName]
        if (shape == null) {
            shape = when (shapeName.lowercase()) {
                "круг" -> Circle()
                "квадрат" -> Square()
                "точка" -> Point()
                else -> throw UnsupportedOperationException("Неподдерживаемая фигура: $shapeName")
            }
            shapes[shapeName] = shape
        }
        return shape
    }

    companion object {

        private val shapes: MutableMap<String, Shape> = HashMap()
    }
}
