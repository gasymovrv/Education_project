package coursera.oop.equality

open class SimpleResource(val id: Long) {

    lateinit var location: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleResource

        if (id != other.id) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }
}

class SimpleBook(
    id: Long,
    val isbn: String
) : SimpleResource(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as SimpleBook

        if (isbn != other.isbn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + isbn.hashCode()
        return result
    }
}

fun main() {
    val sb1 = SimpleBook(1, "isbn")
    sb1.location = "location"

    val sb2 = SimpleBook(1, "isbn")
    sb2.location = "location2"

    println(sb1 == sb2)//false
}
