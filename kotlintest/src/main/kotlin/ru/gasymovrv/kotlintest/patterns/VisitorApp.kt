package ru.gasymovrv.kotlintest.patterns

object VisitorApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val car: Element = CarElement()
        println("HooliganVisitor:")
        car.accept(HooliganVisitor())

        println()
        println("MechanicVisitor:")
        car.accept(MechanicVisitor())
    }
}

//-----------------------------------------------Elements------------------------------------------------
//Element
interface Element {

    fun accept(visitor: Visitor)
}

//ConcreteElement 1 - Кузов
class BodyElement : Element {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

//ConcreteElement 2 - Двигатель
class EngineElement : Element {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

//ConcreteElement 3 - Колесо
class WheelElement(val name: String) : Element {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

//ConcreteElement 4 - Автомобиль (реализован по шаблону Компоновщик)
class CarElement : Element {

    private val elements =
        arrayOf(
            WheelElement("переднее левое"),
            WheelElement("переднее правое"),
            WheelElement("заднее левое"),
            WheelElement("заднее правое"),
            BodyElement(),
            EngineElement()
        )

    override fun accept(visitor: Visitor) {
        for (elem in elements) {
            elem.accept(visitor)
        }
        visitor.visit(this)
    }
}

//-----------------------------------------------Visitors------------------------------------------------
//Посетитель (Visitor)
interface Visitor {

    fun visit(engine: EngineElement?)
    fun visit(body: BodyElement?)
    fun visit(car: CarElement?)
    fun visit(wheel: WheelElement)
}

//ConcreteVisitor 1
class HooliganVisitor : Visitor {

    override fun visit(wheel: WheelElement) {
        println("Пнул " + wheel.name + " колесо")
    }

    override fun visit(engine: EngineElement?) {
        println("Завел двигатель")
    }

    override fun visit(body: BodyElement?) {
        println("Постучал по корпусу")
    }

    override fun visit(car: CarElement?) {
        println("Покурил внутри машины")
    }
}

//ConcreteVisitor 2
class MechanicVisitor : Visitor {

    override fun visit(wheel: WheelElement) {
        println("Подкачал " + wheel.name + " колесо")
    }

    override fun visit(engine: EngineElement?) {
        println("Проверил двигатель")
    }

    override fun visit(body: BodyElement?) {
        println("Отполировал кузов")
    }

    override fun visit(car: CarElement?) {
        println("Проверил внешний вид автомобиля")
    }
}

