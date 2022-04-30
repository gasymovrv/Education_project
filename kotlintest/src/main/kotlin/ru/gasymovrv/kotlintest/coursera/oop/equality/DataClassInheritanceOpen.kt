package ru.gasymovrv.kotlintest.coursera.oop.equality


open class ResourceOpen(
  open val id: Long,
  open val location: String
)

data class BookOpen(
  val isbn: String,
  override val id: Long,
  override val location: String
) : ResourceOpen(id, location)

fun main() {
  val b1 = BookOpen("isbn", 1, "location")
  val b2 = BookOpen("isbn", 2, "location")
  println(b1 == b2)//false
}
