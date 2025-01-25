package ru.gasymovrv.kotlintest.coursera.nullable

data class Info(
    val name: String?,
    val child: Info?
)

fun main() {
    val empty: Info? = null
    val info: Info? = Info("info", null)
    val infoWithChild: Info? = Info("infoWithChild", Info(null, null))
    //val d: String = null //does not compile

    println("empty?.name =  ${empty?.name}")
    println("empty?.child?.child?.child?.name = ${empty?.child?.child?.child?.name}")
    println("infoWithChild?.name = ${infoWithChild?.name}")
    println("infoWithChild?.child?.name = ${infoWithChild?.child?.name}")
    println("info?.child ?: info = ${info?.child ?: info}") //Elvis operator
    println("infoWithChild?.child?.name ?: info = ${infoWithChild?.child?.name ?: "default name"}") //Elvis operator
    println("infoWithChild?.child!!.child?.child?.name = ${infoWithChild?.child!!.child?.child?.name}") //throws NPE if first child or infoWithChild is null
    //println("info?.child!!.name = ${ info?.child!!.name }") //throws NPE
    //println("infoWithChild.child.name = ${ infoWithChild.child }") //does not compile

    val str: String = empty?.child?.name ?: "default name" // Elvis operator converts to non-nullable type
    println(str)

    println(infoWithChild.child.child == info?.child) //does not require ?. because of !! above

    //cast with checking
    val any1: Any = "smart cast"
    if (any1 is String) {
        //val s = any1 as String //this line is not necessary
        printString(any1)
    }

    //safe cast
    val any2: Any = Info("wrong type", null)
    printString(any2 as? String ?: "safe cast")
}

fun printString(str: String) {
    println(str)
}
