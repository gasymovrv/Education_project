package coursera.oop.equality


abstract class ResourceAbstract {

    abstract val id: Long
    abstract val location: String
}

data class BookAbstract(
    val isbn: String,
    override val id: Long,
    override val location: String
) : ResourceAbstract()

fun main() {
    val b1 = BookAbstract("isbn", 1, "location")
    val b2 = BookAbstract("isbn", 2, "location")
    println(b1 == b2)//false
}
