package coursera.oop

import coursera.oop.generics.average
import coursera.oop.generics.demoIn
import coursera.oop.generics.demoOut
import coursera.oop.generics.ensureTrailingPeriod
import coursera.oop.generics.filterNotNull
import coursera.oop.generics.useFoo
import coursera.oop.generics.usePrint
import kotlin.reflect.KCallable

fun main(args: Array<String>) {
    val c3 = C3()
    printMethodInfo(c3::f1)
    //printMethodInfo(c3::f1Protected)
    printMethodInfo(c3::f2)
    printMethodInfo(c3::f3)
    //printMethodInfo(c3::f4)
    //printMethodInfo(c3::f5)
    printMethodInfo(c3::f6)

    Rectangle(10)
    Person2("Person2-name", 30)
    Child1("ch1")
    Child3()

    println(Color.RED.rgb())
    println(Color.BLUE.rgb())
    println(Color.RED.getDescription())

    val ivan1 = Contact("+79564100001", "Ivan", "Moscow, prospect Lenina, 1")
    ivan1.nickname = "abc"
    val ivan2 = Contact("+79564100002", "Ivan", "Moscow, prospect Lenina, 1")
    ivan2.nickname = "123"

    println("ivan1 == ivan2: ${ivan1 == ivan2}")//true
    println("ivan1 === ivan2: ${ivan1 === ivan2}")//false

    val ivanCopy = ivan1.copy()
    println(ivan1)
    println("ivanCopy == ivan1: ${ivanCopy == ivan1}")//true
    println("ivanCopy === ivan1: ${ivanCopy === ivan1}")//false

    println(eval(Num(1)))
    println(eval(Multiply(Sum(Num(1), Num(5)), Num(2))))

    Outer1.Nested(1)
    Outer2(15).Inner(1)

    val kSingleton = KSingleton
    KSingleton.print()
    KSingleton.print()
    KSingleton.print()

    useObjectExpression()

    println(SimpleCompanion.i)
    SimpleCompanion.i++
    println(SimpleCompanion.i)

    SimpleCompanion.foo()
    SimpleCompanion.bar()

    SimpleCompanion.extFun()

    println(CompanionImplInterface.create())
    println(CompanionImplInterface.create())
    println(CompanionImplInterface.create())
    CustomSingleton.INSTANCE
    CustomSingleton.INSTANCE

    println(a)
    println(empty)

    Obj1.prop
    Obj1.prop2
    Class1().prop

    usePrint(listOf(null, "s"))
    useFoo(listOf("s1", "s2"))
    println(filterNotNull(listOf("s1", null, "s2")))

    val sb = StringBuilder("sdgsdgh")
    ensureTrailingPeriod(sb)
    println(sb)

    println(listOf(0, 2, 4, 6).average())
    println(listOf(0.0, 1.4, 3.5).average())

    println(Point(1, 1) + Point(2, 2))

    val point = AssignablePoint(10, 10)
    point += AssignablePoint(1, 1)
    println(point)

    val myList = MyList<String>()
    myList.add("a")
    myList.add("b")
    myList.add("c")
    for (el in myList) {
        println(el)
    }

    demoOut()
    demoIn()

    val (first, second, third, fourth, fifth)
        = ManyFields(1, 2.0, 3000000000L, 13, "example of regular class destructuring")
    val (first1, second1, third1, fourth1, fifth1)
        = DataManyFields(1, 2.0, 3000000000L, 13, "example of data class destructuring")
    println(fifth)
    println(fifth1)

    val getSetExample = GetSetExample(arrayOf("abc", "efg"))
    getSetExample[0] = "xyz"
    println("${getSetExample[0]}, ${getSetExample[1]}")
}

fun printMethodInfo(f: Lambda) {
    println(
        """
    ${f.visibility} 
    ${if (f.isFinal) "final" else "open"} 
    ${f.name} 
    ${if (f.isAbstract) "abstract" else ""}
    """
            .trimIndent().replace("\n", "")
    )
}

typealias Lambda = KCallable<Any>
