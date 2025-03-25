package patterns.builder

/**
 * Реализация строителя с директором
 */
object BuilderApp1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val director = Director()
        director.setBuilder(FordMondeoBuilder())
        val car = director.buildCar()
        println(car)
        director.setBuilder(SubaruBuilder())
        val car2 = director.buildCar()
        println(car2)
    }
}

//----------------------------Builder----------------------------------
//AbstractBuilder
abstract class AbstractCarBuilder {

    //getResult
    var car: Car = Car()

    //buildPart
    abstract fun buildMaker(): AbstractCarBuilder

    //buildPart
    abstract fun buildTransmission(): AbstractCarBuilder

    //buildPart
    abstract fun buildMaxSpeed(): AbstractCarBuilder
}

//ConcreteBuilder 1
class FordMondeoBuilder : AbstractCarBuilder() {

    override fun buildMaker(): AbstractCarBuilder {
        car.maker = "Ford Mondeo"
        return this
    }

    override fun buildTransmission(): AbstractCarBuilder {
        car.transmission = Transmission.AUTO
        return this
    }

    override fun buildMaxSpeed(): AbstractCarBuilder {
        car.maxSpeed = 260
        return this
    }
}

//ConcreteBuilder 2
class SubaruBuilder : AbstractCarBuilder() {

    override fun buildMaker(): AbstractCarBuilder {
        car.maker = "Subaru"
        return this
    }

    override fun buildTransmission(): AbstractCarBuilder {
        car.transmission = Transmission.MANUAL
        return this
    }

    override fun buildMaxSpeed(): AbstractCarBuilder {
        car.maxSpeed = 320
        return this
    }
}

//----------------------------Director----------------------------------
class Director {

    private var builder: AbstractCarBuilder? = null
    fun setBuilder(b: AbstractCarBuilder?) {
        builder = b
    }

    //construct
    fun buildCar(): Car {
        builder!!.buildMaker().buildTransmission().buildMaxSpeed()
        return builder!!.car
    }
}
