package javapro.classes

open class Parent(val str: String, val intDef: Int = 0) {
    val member1: String? = null
    val member2: String = ""
    var member3: String = ""
        set(value) {
            if (value != "not_valid") field = value
        }

    open fun method1() {
        throw UnsupportedOperationException()
    }
}
