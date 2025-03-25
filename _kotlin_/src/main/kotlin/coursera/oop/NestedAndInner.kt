package coursera.oop

// --------------- nested/inner ---------------------
open class Outer1 {

    init {
        println("Outer1 is built")
    }

    class Nested(val value: Int) {
        init {
            println("Nested is built, value: $value")
        }
    }
}

class Outer2(private val value: Int) {
    init {
        println("Outer2 is built")
    }

    inner class Inner(val num: Int) {
        init {
            println("Inner is built, num: $num, value from Outer2: ${this@Outer2.value}")
        }
    }
}

//Example of using protected for a nested class
//open class Outer1 {
//  init {
//      println("Outer1 is built")
//  }
//
//  protected open class Nested(var value: Int) {
//    init {
//      println("Nested is built, value: $value")
//    }
//  }
//}
//
//class Outer2: Outer1() {
//  init {
//      println("Outer2 is built")
//  }
//
//  private inner class Inner(var num: Int): Nested(num) {
//    init {
//      println("Inner is built, value: $value")
//    }
//  }
//}
