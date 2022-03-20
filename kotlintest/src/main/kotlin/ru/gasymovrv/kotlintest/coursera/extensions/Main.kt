package ru.gasymovrv.kotlintest.coursera.extensions

import ru.gasymovrv.kotlintest.coursera.ranges.Computer
import java.math.BigDecimal

fun String.lastChar() = this[length - 1]

fun String.secondChar() = this[1]

fun String.repeat(n: Int): String {
  val sb = StringBuilder(n * length)
  for (i in 1..n) {
    sb.append(this)
  }
  return sb.toString()
}

fun String.get(index: Int) = '*' //Does not work because the get method already defined in String
fun CharSequence.split( //But other extension from library we can override
  vararg delimiters: String,
  ignoreCase: Boolean = false,
  limit: Int = 0
): List<String> = listOf("firstPart")

fun Int.incBy4() = this + 4

fun Computer.upgradeHdd(value: Int) {
  this.rom += value
}

fun BigDecimal.myFunc() = BigDecimal.ZERO

infix fun <T> T.eq(other: T) {
  if (this == other) println("ok")
  else println("Error: $this != $other ")
}

fun main() {
  println("sdfsdg".get(1))
  println("sdfsdg".lastChar())
  println("21".secondChar())
  println("21".repeat(2))
  println(3.incBy4())

  val c = Computer(rom = 500, cpuCores = 6, cpuFrequency = 2400, ram = 10)
  c.upgradeHdd(100)
  println(c)

  val s1 = """
          I
          learn  
          Kotlin  
          """
    .trimIndent()
  println(s1)

  val s2 = """
          I
          |learn 
          |Kotlin
          """
    .trimMargin()
  println(s2)

  val c2 = Computer(rom = 1000, cpuCores = 8, cpuFrequency = 2600, ram = 16)
  val c3 = Computer(rom = 1000, cpuCores = 8, cpuFrequency = 2600, ram = 16)
  c.eq(c2)
  c eq c2
  c2 eq c3

  println("sdg".split("/"))
}