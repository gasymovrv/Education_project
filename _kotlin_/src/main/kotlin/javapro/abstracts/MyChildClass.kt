package javapro.abstracts

class MyChildClass(override var str: String) : AbstractClass(str) {
    override val abstrMember: String = "My overridden abstrMember"

    override fun printMembers() {
        println("abstrMember='$abstrMember', str='$str', member1='$member1', member2='$member2', member3='$member3'")
    }
}
