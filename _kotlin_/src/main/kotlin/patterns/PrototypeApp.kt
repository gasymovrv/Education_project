package patterns

object PrototypeApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val original = Human(18, "Vasya")
        println(original)

        val copy = original.copy() as Human
        println(copy)

        val factory = HumanFactory(copy)
        val h1 = factory.makeCopy()
        println(h1)

        factory.setPrototype(Human(30, "Валерия"))
        val h2 = factory.makeCopy()
        println(h2)
    }
}

interface Copyable {

    fun copy(): Any
}

class Human(var age: Int, var name: String) : Copyable {

    override fun toString(): String {
        return "Human [age=$age, name=$name]"
    }

    override fun copy(): Any {
        val copy = Human(age, name)
        return copy
    }
}

class HumanFactory(human: Human?) {

    var human: Human? = null

    init {
        setPrototype(human)
    }

    fun setPrototype(human: Human?) {
        this.human = human
    }

    fun makeCopy(): Human {
        return human!!.copy() as Human
    }
}
