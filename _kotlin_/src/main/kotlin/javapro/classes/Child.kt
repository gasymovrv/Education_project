package javapro.classes

class Child(private val myStr: String) : Parent(myStr.plus("_str_from_parent")) {
    override fun method1() {
        member3 = "not_valid"
        println("myStr='$myStr', str='$str', intDef='$intDef', member1='$member1', member2='$member2', member3='$member3'")
    }
}
