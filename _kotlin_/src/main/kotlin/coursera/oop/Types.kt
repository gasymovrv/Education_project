package coursera.oop

import java.io.File

//Click Kotlin Bytecode -> Decompile

/*
  public static final int i1 = 1;
*/
const val i1: Int = 1

/*
  private static final int i2 = 1;
  public static final int getI2() {
        return i2;
  }
*/
val i2: Int = 1

/*
  public static final int foo() {
        return 1;
  }
*/
fun foo(): Int = 1

/*
  @Nullable
  public static final Integer foo() {
        return null;
  }
*/
fun foo2(): Int? = null

/*
  @NotNull
  public static final List list = CollectionsKt.listOf(new Integer[]{1, 3, 4, 6});
*/
@JvmField
val list: List<Int> = listOf(1, 3, 4, 6)

/*
  @NotNull
  public static final Integer[] array = new Integer[]{1, 3, 4, 6};
*/
@JvmField
val array: Array<Int> = arrayOf(1, 3, 4, 6)

/*
  @NotNull
  public static final int[] arrayPrimitives = new int[]{1, 3, 4, 6};
*/
@JvmField
val arrayPrimitives: IntArray = intArrayOf(1, 3, 4, 6)

/*
  public static final void autoboxing(@NotNull Object any) {...}
*/
fun autoboxing(any: Any) {
    println("autoboxing: any = $any, i.class = ${any.javaClass}")
}

/*
  public static final void noAutoboxing(int i) {...}
*/
fun noAutoboxing(i: Int) {
    println("noAutoboxing: i = $i, i.class = ${i.javaClass}")
}

inline fun inlinedFun(lambda: () -> Boolean) {
    if (lambda()) {
        println("Success")
    }
}

fun fail(): Nothing {
    println("Nothing for throwing exception")
    throw IllegalArgumentException()
}

fun failNullableNothing(b: Boolean): Nothing? {
    println("Nullable Nothing for throwing exception")
    return if (!b) {
        throw IllegalArgumentException()
    } else {
        null
    }
}

fun infiniteLoop(): Nothing {
    while (true) {
        println("Nothing for infinite loop")
    }
}

fun unitExample(): Unit {
    println("unitExample")
    return Unit //you can omit it
}

//typealias examples (just to change name of something):
typealias FileTable<K> = MutableMap<K, MutableList<File>>
typealias MyHandler = (Int, String, Any) -> Unit
typealias Predicate<T> = (T) -> Boolean

fun main() {
    autoboxing(123)
    noAutoboxing(123)

    /*
    if (true) {
      String var2 = "Success";
      System.out.println(var2);
    }
    */
    inlinedFun { i1 > 0 }

    //Use Nothing for correct types
    val int: Int = if (true) 42 else fail()
    //If replace Nothing with Unit we will have to define int2 as Any
    val int2: Any = if (true) 42 else unitExample()

    val unknown = null //Nothing?

    val s1 = JavaClass.getJavaString()
    val s2: String? = JavaClass.getJavaString()
    //val s3: String = JavaClass.getJavaString() //NPE
    val s4 = JavaClass.getNullableJavaString()
    //println(s1.length) //NPE
    //println(s2.length) //Compilation error
    println(s1?.length)
    println(s2?.length)
    println(s4?.length)
}
