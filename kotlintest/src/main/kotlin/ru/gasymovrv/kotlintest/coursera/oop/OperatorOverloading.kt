package ru.gasymovrv.kotlintest.coursera.oop

data class Point(val x: Int, val y: Int)
data class AssignablePoint(var x: Int, var y: Int)

operator fun Point.plus(point: Point): Point {
    return Point(this.x + point.x, this.y + point.y)
}

operator fun AssignablePoint.plusAssign(point: AssignablePoint) {
    this.x += point.x
    this.y += point.y
}

class MyList<T> {
    private val list: MutableList<T> = mutableListOf()

    fun get(i: Int): T {
        return list[i]
    }

    fun add(el: T): Boolean {
        return list.add(el)
    }

    operator fun iterator(): Iterator<T> = this.list.iterator()
}

class ManyFields(val a: Int, val b: Double, val c: Number, val d: Int, val e: String) {
    operator fun component1() = a
    operator fun component2() = b
    operator fun component3() = c
    operator fun component4() = d
    operator fun component5() = e
}

data class DataManyFields(val a: Int, val b: Double, val c: Number, val d: Int, val e: String)

class GetSetExample<T>(private val array: Array<T>) {
    operator fun get(i: Int): T {
        return array[i]
    }

    operator fun set(i: Int, value: T) {
        array[i] = value
    }
}

