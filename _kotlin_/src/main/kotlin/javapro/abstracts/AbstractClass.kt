package javapro.abstracts

abstract class AbstractClass(open var str: String) {
    abstract val abstrMember: String
    val member1: String? = null
    val member2: String = ""
    var member3: String = ""
        set(value) {
            if (value != "not_valid") field = value
        }

    abstract fun printMembers()

    override fun toString(): String {
        return super.toString()
    }
}
