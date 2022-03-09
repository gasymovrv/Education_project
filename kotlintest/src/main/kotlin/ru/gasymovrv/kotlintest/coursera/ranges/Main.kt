package ru.gasymovrv.kotlintest.coursera.ranges

fun main() {

  for (i in 3 until 5) {
    print("$i ")
  }
  println()
  for (i in 3 downTo 1) {
    print("$i ")
  }
  println()
  for (i in 6 downTo 1 step 2) {
    print("$i ")
  }
  println()

  val chars = 'а'..'я'
  for (ch in chars step 5) {
    print("$ch ")
  }
  println()

  val nums = 1..5
  val decimals = 0.5..1.0
  for (n in nums) {
    val random = Math.random()
    if (random in decimals) {
      println(random)
    }
  }

  println("s in abc..def = ${"s" in "abc".."def"}")
  println("kotlin in Java..Scala = ${"Kotlin" in "Java".."Scala"}")//lexicographical

  //Custom range
  val computer1 = Computer(rom = 500, cpuCores = 8, cpuFrequency = 2400, ram = 8)
  val computer2 = Computer(rom = 240, cpuCores = 8, cpuFrequency = 2400, ram = 16)
  val computer3 = Computer(rom = 500, cpuCores = 6, cpuFrequency = 2400, ram = 10)

  println(computer1 >= computer2)
  println(computer3 >= computer1)
  println(computer3 in computer1..computer2)

  for (c in computer1) {
    println(c)
  }
}