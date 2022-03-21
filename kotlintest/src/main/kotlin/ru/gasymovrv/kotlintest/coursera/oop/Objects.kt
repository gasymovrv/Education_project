package ru.gasymovrv.kotlintest.coursera.oop

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
class SimpleCompanion2 {
  companion object CompanionObj
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

class CustomSingleton private constructor() {
  init {
    println("Custom singleton created!")
  }

  companion object {
    val INSTANCE: CustomSingleton by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CustomSingleton() }
  }
}