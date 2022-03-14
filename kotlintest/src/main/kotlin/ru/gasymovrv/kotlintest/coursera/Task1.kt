package ru.gasymovrv.kotlintest.coursera

// Implement the function that checks whether a string is a valid identifier.
// A valid identifier is a non-empty string that starts with a letter or underscore and consists of only letters, digits and underscores.
fun isValidIdentifier(s: String): Boolean {
  val array = s.toCharArray()
  return when {
    s.isEmpty() -> false
    array.isNotEmpty() && !array.first().isLetter() && array.first() != '_' -> false
    s.contains(Regex("[^_\\d\\w]")) -> false
    else -> true
  }
}

fun isValidIdentifier2(s: String): Boolean {
  fun isValidChar(ch: Char) = ch == '_' || ch.isLetterOrDigit()
  if (s.isEmpty() || s[0].isDigit()) return false

  for (ch in s) {
    if (!isValidChar(ch)) return false
  }
  return true
}

fun main() {
  println(isValidIdentifier("name"))   // true
  println(isValidIdentifier("_name"))  // true
  println(isValidIdentifier("_12"))    // true
  println(isValidIdentifier(""))       // false
  println(isValidIdentifier("012"))    // false
  println(isValidIdentifier("no$"))    // false
}