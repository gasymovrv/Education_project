package ru.gasymovrv.kotlintest.coursera.properties

import ru.gasymovrv.kotlintest.coursera.properties.State.OFF
import ru.gasymovrv.kotlintest.coursera.properties.State.ON

class StateLogger {

  var state = false
    set(value) {
      println("state has changed: $field -> $value")
      field = value
    }
}

enum class State { ON, OFF }

class StateLogger2 {
  private var boolState = false

  //field doesn't generate, only property (setter and getter)
  var state: State
    get() = if (boolState) ON else OFF
    set(value) {
      println("state2 has changed: $state -> $value")
      boolState = value == ON
    }
}

class LengthCounter {

  var counter: Int = 0
    private set

  fun addWord(word: String) {
    counter += word.length
  }
}