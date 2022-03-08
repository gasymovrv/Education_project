package ru.gasymovrv.kotlintest.coursera.extensions

import ru.gasymovrv.kotlintest.coursera.ranges.Computer

fun String.lastChar() = this[length - 1]
fun String.secondChar() = this[1]
fun String.repeat(n: Int): String {
  val sb = StringBuilder(n * length)
  for (i in 1..n) {
    sb.append(this)
  }
  return sb.toString()
}

fun Int.incBy4() = this + 4
fun Computer.upgradeHdd(value: Int) {
  this.hdd += value
}

fun main() {
  println("sdfsdg".lastChar())
  println("21".secondChar())
  println("21".repeat(2))
  println(3.incBy4())

  val c = Computer(hdd = 500, cpuCores = 6, cpuFrequency = 2400, ram = 10, rom = 500)
  c.upgradeHdd(100)
  println(c)
}