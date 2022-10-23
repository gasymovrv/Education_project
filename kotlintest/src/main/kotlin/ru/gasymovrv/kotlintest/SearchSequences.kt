package ru.gasymovrv.kotlintest

fun main() {
  val startTime = System.currentTimeMillis()
  // Expected 100000 repeats
  println(
    calculateSumOfSequentialRepeats(
      "ABC!",
      // 5 repeats
      "2ABC!5_ABC!ABC!123hgdABC!h_ABC!ABC!ABC!vcABC!jABC!".repeat(20000) // one million chars
    )
  )
  println("Execution time: ${System.currentTimeMillis() - startTime} ms")
}

fun calculateSumOfSequentialRepeats(short: String, long: String): Int {
  if (short.isBlank() || long.isBlank()) {
    return 0
  }

  var i = 0
  val shortLength = short.length
  val longLength = long.length
  var sequentialRepeats = 0
  var firstInSequence = true

  while (i < longLength) {
    val nextIndex = long.indexOf(short, i)
    if (nextIndex == -1) {
      return sequentialRepeats
    }

    if (nextIndex == i) {
      if (firstInSequence) {
        sequentialRepeats++
      }
      sequentialRepeats++
      firstInSequence = false
    } else {
      firstInSequence = true
    }
    i = nextIndex + shortLength
  }
  return sequentialRepeats
}
