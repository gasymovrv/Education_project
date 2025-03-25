package patterns.monad

import java.util.function.Function

fun main() {
    val obj = TestClass("name 1")
    Monad.from(obj)
        .map(TestClass::name)
        .map { it: String -> it[0] }
        .map { it: Char? ->
            println(it)
            it
        }
}

data class TestClass(val name: String)

class Monad<T> private constructor(private val value: T) {

    fun <R> map(func: Function<T, R>): Monad<R> {
        return Monad(func.apply(value))
    }

    companion object {

        fun <T> from(value: T): Monad<T> {
            return Monad(value)
        }
    }
}
