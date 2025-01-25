package ru.gasymovrv.kotlintest.javapro.sealed

sealed class Optional<out V> {
    abstract fun isPresent(): Boolean
}

data class Some<out V>(val value: V) : Optional<V>() {
    override fun isPresent(): Boolean = true
}

class None<out V> : Optional<V>() {
    override fun isPresent(): Boolean = false
}

fun randomValue(): Optional<Double> {
    val random = Math.random()
    return if (random > 0.5) Some(random) else None()
}
