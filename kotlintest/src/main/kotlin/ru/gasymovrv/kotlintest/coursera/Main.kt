package ru.gasymovrv.kotlintest.coursera

import ru.gasymovrv.kotlintest.coursera.extensions.lastChar

fun main() {
  val set = hashSetOf(1, 7, 53)
  val list = arrayListOf(1, 7, 53) //the same as mutableListOf()
  val map = hashMapOf(
    1 to "one",
    7 to "seven",
    53 to "fifty-three"
  )

  println(set.javaClass)
  println(list.javaClass)
  println(map.javaClass)

  println("hello")
  val mutableList = mutableListOf(1, 3, 43, 4242, 11)
  val a: Int = 1

  var arr: Array<Int> = emptyArray()
  mutableList[1] = 4
  println(mutableList.joinToString(", ", "[", "]"))

  val d1 = 1.267_967_990
  val d2 = 2.0
  println(d1 / d2)
  println((d1 / d2) == 0.633983995)

  println("print list:")
  for ((i, el) in mutableList.withIndex()) {
    println("#$i: $el")
  }

  val map2 = mapOf(
    "str1" to "value1",
    "str2" to "value2",
    "str3" to "value3"
  )
  for ((k, v) in map2) {
    println("key '$k' : value '$v'")
  }

  for (ch in "abc") {
    print("char: ${ch}, code: ")
    println(ch.code)
  }

  for (ch in "abc") {
    print("char: ${ch + 1}, code: ")
    println(ch.code + 1)
  }

  println("sd".lastChar())
}