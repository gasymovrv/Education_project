package ru.gasymovrv.kotlintest.coursera.properties

fun main(args: Array<String>) {
    println("$foo1 $foo1 $foo2 $foo2")

    val logger = StateLogger()
    logger.state = true

    val lengthCounter = LengthCounter()
//  lengthCounter.counter = 2 //does not compile because counter is private
    lengthCounter.addWord("I")
    lengthCounter.addWord("learn")
    lengthCounter.addWord("kotlin")
    println(lengthCounter.counter)

    val logger2 = StateLogger2()
    println(logger2.state)
    logger2.state = State.ON
    println(logger2.state)

    val jObj = JavaClass("sdg")
    jObj.isActive = true
    println(jObj)

    val facebookUser: User = FacebookUser(123)
    val subscribingUser: User = SubscribingUser("user1@mail.com")
    println(facebookUser.nickname)
    println(facebookUser.nickname)
    println(subscribingUser.nickname)
    println(subscribingUser.nickname)

    val s = "abc"
    println(s.medianChar)
    println(s.medianChar)

    val sb = StringBuilder("abc")
    println(sb)
    sb.lastCHar = 'd'
    println(sb)

    println(facebookUser::nickname.name)
    println(facebookUser::nickname.isAbstract)
    println(facebookUser::nickname.isFinal)
    println(facebookUser::nickname.isOpen)

    val lateInitExample = LateInitExample()
    //lateInitExample.lateinitVar //throws UninitializedPropertyAccessException
    lateInitExample.initializeLogic()
}
