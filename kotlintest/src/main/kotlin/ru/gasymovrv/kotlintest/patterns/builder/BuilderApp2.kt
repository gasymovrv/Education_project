package ru.gasymovrv.kotlintest.patterns.builder

/**
 * Реализация строителя без директора
 */
object BuilderApp2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val car = CarBuilder()
            .buildMake("Mercedes")
            .buildTransmission(Transmission.AUTO)
            .buildMaxSpeed(280)
            .build()
        println(car)
    }
}

//ConcreteBuilder
class CarBuilder {

    private var m = "Default"
    private var t = Transmission.MANUAL
    private var s = 120

    //buildPart
    fun buildMake(m: String): CarBuilder {
        this.m = m
        return this
    }

    //buildPart
    fun buildTransmission(t: Transmission): CarBuilder {
        this.t = t
        return this
    }

    //buildPart
    fun buildMaxSpeed(s: Int): CarBuilder {
        this.s = s
        return this
    }

    //getResult
    fun build(): Car {
        val car = Car()
        car.maker = m
        car.transmission = t
        car.maxSpeed = s
        return car
    }
}
