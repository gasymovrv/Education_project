package patterns

object IteratorApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val c = Tasks()

        val it = c.iterator

        while (it.hasNext()) {
            println(it.next() as String?)
        }
    }
}

//Iterator
interface Iterator {

    fun hasNext(): Boolean
    fun next(): Any?
}

//Aggregate
interface Container {

    val iterator: Iterator
}

//ConcreteAggregate
class Tasks : Container {

    private val tasks = arrayOf("Построить дом", "Вырастить сына", "Посадить дерево")

    override val iterator: Iterator
        get() = TaskIterator()

    //ConcreteIterator (у стандартных коллекций также реализовано внутренним классом)
    private inner class TaskIterator : Iterator {

        var index: Int = 0

        override fun hasNext(): Boolean {
            return index < tasks.size
        }

        override fun next(): Any {
            return tasks[index++]
        }
    }
}
