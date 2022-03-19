package ru.gasymovrv.kotlintest.coursera.collections

import java.util.TreeMap

fun main() {
  println("immutable:")
  println(listOf<String>("a", "b").javaClass)//Arrays$ArrayList
  println(setOf<String>("s", "d").javaClass)//LinkedHashSet
  println(mapOf<String, Int>("a" to 1, "b" to 2).javaClass)//LinkedHashMap

  println("mutable:")
  val strArray = arrayOf("a1", "c2") //only exists elements are mutable, can't change size
  val intArray = intArrayOf(1, 2) //only exists elements are mutable, can't change size
  println(strArray.javaClass)//Array of strings
  println(intArray.javaClass)//Array of integers
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
}

data class SortedType(val string: String): Comparable<SortedType> {
  override operator fun compareTo(other: SortedType): Int {
    return string.compareTo(other.string)
  }
}

//It works only for operator overloading
//operator fun SortedType.compareTo(other: SortedType): Int {
//  return string.compareTo(other.string)
//}