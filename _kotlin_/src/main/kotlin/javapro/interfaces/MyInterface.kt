package javapro.interfaces

interface MyInterface : MyInterface2, MyInterface3 {
    val member: String

    fun method1()

    fun defaultMethod() {
        println("default")
    }
}
