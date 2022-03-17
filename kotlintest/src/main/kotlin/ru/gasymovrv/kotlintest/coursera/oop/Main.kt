package ru.gasymovrv.kotlintest.coursera.oop

import kotlin.reflect.KCallable

fun main(args: Array<String>) {
  val c3 = C3()
  printMethodInfo(c3::f1)
  //printMethodInfo(c3::f1Protected)
  printMethodInfo(c3::f2)
  printMethodInfo(c3::f3)
  //printMethodInfo(c3::f4)
  //printMethodInfo(c3::f5)
  printMethodInfo(c3::f6)

  Rectangle(10)
  Person2("Person2-name", 30)
  Child1("ch1")
  Child3()

  println(Color.RED.rgb())
  println(Color.BLUE.rgb())
  println(Color.RED.getDescription())

  val ivan1 = Contact("+79564100001", "Ivan", "Moscow, prospect Lenina, 1")
  ivan1.nickname = "abc"
  val ivan2 = Contact("+79564100002", "Ivan", "Moscow, prospect Lenina, 1")
  ivan2.nickname = "123"

  println("ivan1 == ivan2: ${ivan1 == ivan2}")//true
  println("ivan1 === ivan2: ${ivan1 === ivan2}")//false

  val ivanCopy = ivan1.copy()
  println(ivan1)
  println("ivanCopy == ivan1: ${ivanCopy == ivan1}")//true
  println("ivanCopy === ivan1: ${ivanCopy === ivan1}")//false

  println(eval(Num(1)))
  println(eval(Multiply(Sum(Num(1), Num(5)), Num(2))))

  Outer1.Nested(1)
  Outer2(15).Inner(1)

  val kSingleton = KSingleton
  kSingleton.print()
  kSingleton.print()
  kSingleton.print()

  useObjectExpression()

  println(SimpleCompanion.i)
  SimpleCompanion.i++
  println(SimpleCompanion.i)

  SimpleCompanion.foo()
  SimpleCompanion.bar()

  SimpleCompanion.extFun()

  println(CompanionImplInterface.create())
  println(CompanionImplInterface.create())
  println(CompanionImplInterface.create())

  println(a)
  println(empty)

  Obj1.prop
  Obj1.prop2
  Class1().prop
}

fun printMethodInfo(f: KCallable<Any>) {
  println(
    """
    ${f.visibility} 
    ${if (f.isFinal) "final" else "open"} 
    ${f.name} 
    ${if (f.isAbstract) "abstract" else ""}
    """
      .trimIndent().replace("\n", "")
  )
}
