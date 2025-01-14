package ru.gasymovrv.kotlintest.patterns


object TemplateMethodApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val a: Abstr = A()
        a.templateMethod()

        println()

        val b: Abstr = B()
        b.templateMethod()
    }
}

//AbstractClass
abstract class Abstr {

    fun templateMethod() {
        print("1")
        subMethod1()
        print("3")
        subMethod2()
    }

    abstract fun subMethod1()
    abstract fun subMethod2()
}

//ConcreteClass 1
class A : Abstr() {

    override fun subMethod1() {
        print("2")
    }

    override fun subMethod2() {
        print("4")
    }
}

//ConcreteClass 2
class B : Abstr() {

    override fun subMethod1() {
        print("-------")
    }

    override fun subMethod2() {}
}
