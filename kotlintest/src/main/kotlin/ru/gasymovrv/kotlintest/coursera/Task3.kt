package ru.gasymovrv.kotlintest.coursera

import ru.gasymovrv.kotlintest.coursera.extensions.eq

// Complete the following implementation of 'evaluateGuess()' function.
// Then compare your solution with the solution written in a functional style.
data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPositions = secret.zip(guess).count { it.first == it.second }

    val commonLetters = "ABCDEF".sumOf { ch ->
      Math.min(secret.count { it == ch }, guess.count { it == ch })
    }
  return Evaluation(rightPositions, commonLetters - rightPositions)
}

fun main(args: Array<String>) {
    val result = Evaluation(rightPosition = 1, wrongPosition = 1)
    evaluateGuess("BCDF", "ACEB") eq result
    evaluateGuess("AAAF", "ABCA") eq result
    evaluateGuess("ABCA", "AAAF") eq result
}