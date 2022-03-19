package ru.gasymovrv.kotlintest.coursera.functional

fun main() {
  //brings performance overhead (object for lambda)
  myRun { println("Hi") }
  //in comparison to:
  println("Hi")


  //NO performance overhead, because 'run' is inlined
  run { println("Hi") }
  //in comparison to:
  println("Hi")
}

fun myRun(lambda: ()-> Unit) {
  lambda()
}