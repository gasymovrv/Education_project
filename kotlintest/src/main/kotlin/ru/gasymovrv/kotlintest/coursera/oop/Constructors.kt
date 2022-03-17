package ru.gasymovrv.kotlintest.coursera.oop

class A

class Person1(val name: String, val age: Int) //name and age are properties

class Person2(name: String, age: Int) { //name and age are only constructor parameters

  private val name: String//can be defined in the primary constructor

  init { //this block is for primary constructor
    println("Person2[name=$name, age=$age]")
    this.name = name
    f1()
  }

  private fun f1() {
    println("Person2, property 'name': $name")
    //println(age) //Unresolved reference: age
  }
}

class Person3 internal constructor(val name: String)

class Rectangle(val height: Int, val width: Int) { //primary constructor

  constructor(side: Int) : this(side, side) {//secondary constructor
    println("Square[$height, $width], side: $side")
  }

  init {
    println("Rectangle[$height, $width]")
    //println(side) //Unresolved reference: side
  }
}

open class Parent1(val name: String) {
  init {
    println("parent $name")
  }
}

class Child1(name: String) : Parent1(name) {
  init {
    println("child $name")
  }
}

class Child2 : Parent1 {
  constructor(name: String, param: Int) : super(name)
}

open class Parent2 {

  open val foo = 1

  init {
    //calls getter, and when it calls from child 'foo' is always 0.
    //Do not use open property in constructors of superclass
    println(foo)
  }
}

class Child3 : Parent2() {

  override val foo = 2 //overrides only getter
}