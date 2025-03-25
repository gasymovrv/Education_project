package coursera.oop.equality


open class ResourceAcceptSubclasses(
    val id: Long,
    val location: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ResourceAcceptSubclasses) return false

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

class BookAcceptSubclasses1(id: Long, location: String) :
    ResourceAcceptSubclasses(id, location)

class BookAcceptSubclasses2(id: Long, location: String) :
    ResourceAcceptSubclasses(id, location)

open class ResourceNotAcceptSubclasses(
    val id: Long,
    val location: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceNotAcceptSubclasses

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

class BookNotAcceptSubclasses1(id: Long, location: String) :
    ResourceNotAcceptSubclasses(id, location)

class BookNotAcceptSubclasses2(id: Long, location: String) :
    ResourceNotAcceptSubclasses(id, location)

fun main() {
    val b1: ResourceAcceptSubclasses = BookAcceptSubclasses1(1, "location1")
    val b2: ResourceAcceptSubclasses = BookAcceptSubclasses2(1, "location1")
    println(b1 == b2)//true

    val b3: ResourceNotAcceptSubclasses = BookNotAcceptSubclasses1(1, "location1")
    val b4: ResourceNotAcceptSubclasses = BookNotAcceptSubclasses2(1, "location1")
    println(b3 == b4)//false
}
