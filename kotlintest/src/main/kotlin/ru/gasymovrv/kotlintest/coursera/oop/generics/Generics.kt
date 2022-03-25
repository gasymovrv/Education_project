package ru.gasymovrv.kotlintest.coursera.oop.generics

fun <T> List<T>.print(printFun: (T) -> Unit) { //T can be nullable
  for (element in this) {
    printFun(element)
  }
}

fun <T : Any> foo(list: List<T>) {//T can't be nullable
  for (element in list) {
    println(element)
  }
}

fun <T : Any> filterNotNull(list: List<T?>): List<T> {//T is nullable in argument, but non-nullable in return type
  return list.filterNotNull()
}

fun <T> ensureTrailingPeriod(seq: T): Unit where T : Appendable, T : CharSequence {
  if (!seq.endsWith('.')) {
    seq.append('.')
  }
}

fun List<Int>.average(): Double {
  return sum().toDouble() / size
}

@JvmName("averageOfDouble")
fun List<Double>.average(): Double {
  return sum() / size
}

fun usePrint(strings: List<String?>) {
  strings.print { println(it) }
}

fun useFoo(ints: List<String>) {
  foo(ints)
}

//------------------------ out -------------------------------
//'out' means output, so we can use 'out T' only as return type
interface Producer<out T> {

  fun produce(): T
}

fun demoOut() {
  val p: Producer<Number> = object : Producer<Double> { // This is OK, since T is an out-parameter
    override fun produce(): Double {
      return 1234.1234
    }
  }
  println("produced: ${p.produce()}")
}

//------------------------ in -------------------------------
//'in' means input, so we can use 'in T' only as argument type
interface Consumer<in T> {

  fun consume(other: T)
}

fun demoIn() {
  val y: Consumer<Double> = object : Consumer<Number> {// OK! Number is a parent of Double
    override fun consume(other: Number) {
      println("consumed: $other")
    }
  }
  y.consume(1234.1234)
}