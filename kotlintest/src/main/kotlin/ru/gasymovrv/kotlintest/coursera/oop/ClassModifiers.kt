package ru.gasymovrv.kotlintest.coursera.oop

// --------------- enum ---------------------
enum class Color(val r: Int, val g: Int, val b: Int) {

  RED(255, 0, 0),
  BLUE(0, 0, 255);

  fun rgb() = (r * 256 + g) * 256 + b
}

fun Color.getDescription(): String {
  return when (this) {
    Color.RED -> "hot"
    Color.BLUE -> "cold"
  }
}

// --------------- data ---------------------
data class Contact(val name: String, var address: String) {
  //equals, hashCode, copy, toString were generated only for properties of the primary constructor

  constructor(phone: String, name: String, address: String) : this(name, address) {
    this.phone = phone
  }

  var phone: String? = null//does not use in generated equals/hashCode/copy/toString
  var nickname: String = name//does not use in generated equals/hashCode/copy/toString

  //You can override methods
  //  override fun hashCode(): Int {
  //    return name.hashCode()
  //  }
  //
  //  override fun equals(other: Any?): Boolean {
  //    if (this === other) return true
  //    if (javaClass != other?.javaClass) return false
  //
  //    other as Contact
  //
  //    if (name != other.name) return false
  //
  //    return true
  //  }
}

// --------------- sealed ---------------------
// Sealed classes and interfaces represent restricted class hierarchies that provide more control over inheritance.
// All direct subclasses of a sealed class are known at compile time.
// No other subclasses may appear after a module with the sealed class is compiled.
// For example, third-party clients can't extend your sealed class in their code.
// Thus, each instance of a sealed class has a type from a limited set that is known when this class is compiled.
sealed interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
class Multiply(val left: Expr, val right: Expr) : Expr

fun eval(expr: Expr): Int {
  // If Expr interface was not sealed then we would have here the exception:
  // 'when' expression must be exhaustive, add necessary 'else' branch
  return when (expr) {
    is Num -> expr.value
    is Sum -> eval(expr.left) + eval(expr.right)
    is Multiply -> eval(expr.left) * eval(expr.right)
  }
}

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

// --------------- class delegation ---------------------
interface Repository {

  fun getById(id: Int): Person1
  fun getAll(): List<Person1>
  fun print()
}

interface Logger {

  fun logAll()
  fun print()
}

class Controller(
  repository: Repository,
  private val logger: Logger
) : Repository by repository, Logger by logger {

  //override to solve conflict
  override fun print() {
    return logger.print()
  }
}

fun use(controller: Controller) {
  val person = controller.getById(1)
  val list = controller.getAll()
  controller.logAll()
}

// --------------- object (singleton) ---------------------
object KSingleton {

  var counter: Int = 0

  fun print() {
    println("$this, counter: ${counter++}")
  }
  @JvmStatic fun bar() {//use @JvmStatic only in 'object' or 'companion object'
    println("static function bar")
  }
}

// --------------- object expression (like anonymous class in Java) ---------------------
fun handleLogger(logger: Logger) {
  logger.print()
}

fun useObjectExpression() {
  handleLogger(object : Logger {//it's not singleton, creates new object on each access
    override fun logAll() {
      TODO("Not yet implemented")
    }

    override fun print() {
      println("Logger: call from anonymous class")
    }
  })
}

// --------------- companion object (like static members in Java) ---------------------
class SimpleCompanion {
  companion object {
    var i: Int = 1

    @JvmStatic fun foo() {}
    fun bar() {}
  }
}

interface Factory<T> {
  fun create(): T
}

class CompanionImplInterface private constructor(val name: String) {
  companion object : Factory<CompanionImplInterface> {
    private var i: Int = 1

    override fun create(): CompanionImplInterface {
      return CompanionImplInterface("created by factory : #${i++}")
    }
  }

  override fun toString(): String {
    return "CompanionImplInterface(name='$name')"
  }
}

fun SimpleCompanion.Companion.extFun() = println("extension in companion object")

