package patterns.builder

class Car {

    var maker: String? = null
    var transmission: Transmission? = null
    var maxSpeed = 0

    override fun toString(): String {
        return ("Car [make=" + maker + ", transmission=" + transmission
            + ", maxSpeed=" + maxSpeed + "]")
    }
}

enum class Transmission {
    MANUAL, AUTO
}
