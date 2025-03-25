package coursera.oop.equality


open class Resource {

    var id: Long? = null
    var location: String? = null
}

//Data class doesn't take the super class to generate equals/hashCode and other methods
class Book(
    val isbn: String
) : Resource()

fun main() {
    val b1: Resource = Book("isbn")
    b1.id = 1
    b1.location = "location1"

    val b2: Resource = Book("isbn")
    b2.id = 1
    b2.location = "location1"

    println(b1 == b2)//true
}
