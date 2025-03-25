package coursera.oop

//static public fields will be generated
const val a: Int = 1234 //only primitives and String
const val empty: String = "" //only primitives and String
@JvmField
val b: Int = 5678
@JvmField
val prop = Contact("Name", "Address")

//regular property
val c: Int = 9101112


object Obj1 {
    @JvmField//static public field will be generated
    val prop = Contact("Name", "Address")

    @JvmStatic//generates static GETTER and it's useless
    val prop2 = Contact("Name2", "Address2")
}

class Class1 {
    @JvmField//regular public field will be generated
    val prop = Contact("Name", "Address")
}
