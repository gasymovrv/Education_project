package ru.gasymovrv.kotlintest.coursera.exceptions

import java.io.IOException

fun foo() {
  throw IOException()
}

@Throws(IOException::class)
fun bar() {
  throw IOException()
}