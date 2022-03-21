package ru.gasymovrv.kotlintest.coursera.collections

import java.util.TreeMap
import kotlin.random.Random

fun main() {
  println("immutable:")
  println(listOf<String>("a", "b").javaClass)//Arrays$ArrayList
  println(setOf<String>("s", "d").javaClass)//LinkedHashSet
  println(mapOf<String, Int>("a" to 1, "b" to 2).javaClass)//LinkedHashMap

  println("mutable:")
  val strArray = arrayOf("a1", "c2") //only exists elements are mutable, can't change size
  val intArray = intArrayOf(1, 2) //array of int primitives
  val integerArray = arrayOf(1, 2) //array of Integers
  println(strArray.javaClass)//Array of strings
  println(intArray.javaClass)//Array of integers
  println(integerArray.javaClass)//Array of integers
  println(mutableListOf<String>("a", "b").javaClass)//Arrays$ArrayList
  println(mutableSetOf<String>("s", "d").javaClass)//LinkedHashSet
  println(mutableMapOf<String, Int>("a" to 1, "b" to 2).javaClass)//LinkedHashMap

  println("custom mutable:")
  val set = hashSetOf(1, 7, 53, null)
  val list = arrayListOf(1, 7, 53) //the same as mutableListOf()
  val map = hashMapOf(
    1 to "one",
    7 to "seven",
    53 to "fifty-three"
  )
  set.add(2365)
  set.remove(7)
  list[0] = 55
  map[1] = "one-changed"

  println(set.javaClass)
  println(set)
  println(list.javaClass)
  println(list)
  println(map.javaClass)
  println(map)

  val mutableList = mutableListOf(1, 3, 43, 4242, 11)

  var arr: Array<Int> = emptyArray()
  //arr[0] = 1 //Index 0 out of bounds for length 0
  mutableList[1] = 4
  println(mutableList.joinToString(", ", "[", "]"))

  intArray[0] = 134
  strArray[0] = "b134"
  println(intArray[0])
  println(strArray[0])

  val collection1: List<Int?> = list + set //= list.addAll(set)
  val collection2: Set<Int?> = set + list//= set.addAll(list)
  println(collection1)
  println(collection2)

  var list1 = listOf("a", "b")
  list1 += "c"//creates new list

  val list2 = mutableListOf("a", "b")
  list2 += "c"//= list2.add("c")

  val tm = TreeMap<SortedType, Int>()
  tm[SortedType("z")] = 1
  tm[SortedType("b")] = 2
  tm[SortedType("a")] = 3
  tm[SortedType("c")] = 4
  tm[SortedType("a")] = 5
  println(tm)
  println(SortedType("zbc") > SortedType("abc"))

  //------------ Sequences -------------------
  //Lazy like Java Streams, without creating collections at each step
  val s = listOf("a", "b", "b", "c", "sdf", "dga")
    .asSequence()
    .filter { it.length == 1 }
    .sorted()
    .mapIndexed { i, s -> "${s}_$i" }
    .toList()
  println(s)

  val list3 = listOf(1, 2, 3, 4)
  list3.map(::m).filter(::f)  //m1 m2 m3 m4 f1 f2 f3 f4
  println()
  list3.asSequence().map(::m).filter(::f).toList() //m1 f1 m2 f2 m3 f3 m4 f4
  println()
  list3.asSequence().filter(::f).map(::m).toList() //f1 f2 m2 f3 f4 m4
  println()
  list3.filter(::f).map(::m) //f1 f2 f3 f4 m2 m4
  list3.asSequence().map(::m).filter(::f)//nothing is printed

  println(generateSequence { Random.nextInt(5).takeIf { it > 0 } })
  val infiniteSeq = generateSequence(0) { it + 1 }
  println(infiniteSeq.take(2).toList())
  println(infiniteSeq.take(5).toList())

  val numbers = generateSequence(3) { n ->
    println("Generating element...")
    (n + 1).takeIf { it < 7 }
  }
  println(numbers.first())//3

  println(mySequence().map {it*it}.filter { it > 10 }.first())
}

data class SortedType(val string: String) : Comparable<SortedType> {

  override operator fun compareTo(other: SortedType): Int {
    return string.compareTo(other.string)
  }
}

//It works only for operator overloading, but not for comparison in TreeMap for example
//operator fun SortedType.compareTo(other: SortedType): Int {
//  return string.compareTo(other.string)
//}

fun m(i: Int): Int {
  print("m$i ")
  return i
}

fun f(i: Int): Boolean {
  print("f$i ")
  return i % 2 == 0
}

//Lazy building of sequence
fun mySequence() = sequence {
  println("yield one element")
  yield(1)
  println("yield a range")
  yieldAll(3..5)
  println("yield a list")
  yieldAll(listOf(7, 9))
}
