package ru.gasymovrv.kotlintest.patterns

object BridgeApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val c: Car = Sedan(Kia())
        c.showDetails()

        val c2: Car = Hatchback(Kia())
        c2.showDetails()

        val c3: Car = Sedan(Skoda())
        c3.showDetails()

        val c4: Car = Coupe(Mercedes())
        c4.showDetails()
    }
}

//--------------------------------Abstraction---------------------------------------
abstract class Car(var maker: Maker) {

    //operation
    abstract fun showType()

    fun showDetails() {
        showType()
        maker.showMaker()
        println()
    }
}

class Sedan(m: Maker) : Car(m) {

    override fun showType() {
        println("Sedan")
    }
}

class Hatchback(m: Maker) : Car(m) {

    override fun showType() {
        println("Hatchback")
    }
}

class Coupe(m: Maker) : Car(m) {

    override fun showType() {
        println("Coupe")
    }
}

//--------------------------------Implementor---------------------------------------
interface Maker {

    //operation
    fun showMaker()
}

//ConcreteImplementor 1
class Kia : Maker {

    override fun showMaker() {
        println("Kia")
    }
}

//ConcreteImplementor 2
class Skoda : Maker {

    override fun showMaker() {
        println("Skoda")
    }
}

//ConcreteImplementor 3
class Mercedes : Maker {

    override fun showMaker() {
        println("Mercedes")
    }
}
