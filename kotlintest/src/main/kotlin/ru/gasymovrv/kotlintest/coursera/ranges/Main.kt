package ru.gasymovrv.kotlintest.coursera.ranges

fun main(args: Array<String>) {

  for (i in 3 downTo 1) {
    println(i)
  }
  for (i in 6 downTo 1 step 2) {
    println(i)
  }

  val chars = 'а'..'я'
  for (ch in chars step 5) {
    println(ch)
  }

  val nums = 1..5
  val decimals = 0.5..1.0
  for (n in nums) {
    val random = Math.random()
    if (random in decimals) {
      println(random)
    }
  }

  print("s" in "abc".."def")
  println("Kotlin" in "Java".."Scala")//lexicographical

  //Custom range
  val computer1 = Computer(hdd = 500, cpuCores = 8, cpuFrequency = 2400, ram = 8, rom = 500)
  val computer2 = Computer(hdd = 240, cpuCores = 8, cpuFrequency = 2400, ram = 16, rom = 500)
  val computer3 = Computer(hdd = 500, cpuCores = 6, cpuFrequency = 2400, ram = 10, rom = 500)

  println(computer1 >= computer2)
  println(computer3 >= computer1)
  println(computer3 in computer1..computer2)
}