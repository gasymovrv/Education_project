package coursera.oop

// --------------- enum ---------------------
enum class Color(val r: Int, val g: Int, val b: Int) {

    RED(255, 0, 0),
    BLUE(0, 0, 255);

    fun rgb() = (r * 256 + g) * 256 + b
}

fun Color.getDescription(): String {
    return when (this) {
        Color.RED -> "hot"
        Color.BLUE -> "cold"
    }
}

// --------------- data ---------------------
data class Contact(val name: String, var address: String) {
    //equals, hashCode, copy, toString were generated only for properties of the primary constructor

    constructor(phone: String, name: String, address: String) : this(name, address) {
        this.phone = phone
    }

    var phone: String? = null//does not use in generated equals/hashCode/copy/toString
    var nickname: String = name//does not use in generated equals/hashCode/copy/toString

    //You can override methods
    //  override fun hashCode(): Int {
    //    return name.hashCode()
    //  }
    //
    //  override fun equals(other: Any?): Boolean {
    //    if (this === other) return true
    //    if (javaClass != other?.javaClass) return false
    //
    //    other as Contact
    //
    //    if (name != other.name) return false
    //
    //    return true
    //  }
}

// --------------- sealed ---------------------
// Sealed classes and interfaces represent restricted class hierarchies that provide more control over inheritance.
// All direct subclasses of a sealed class are known at compile time.
// No other subclasses may appear after a module with the sealed class is compiled.
// For example, third-party clients can't extend your sealed class in their code.
// Thus, each instance of a sealed class has a type from a limited set that is known when this class is compiled.
sealed interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
class Multiply(val left: Expr, val right: Expr) : Expr

fun eval(expr: Expr): Int {
    // If Expr interface was not sealed then we would have here the exception:
    // 'when' expression must be exhaustive, add necessary 'else' branch
    return when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left) + eval(expr.right)
        is Multiply -> eval(expr.left) * eval(expr.right)
    }
}

// --------------- class delegation ---------------------
interface Repository {

    fun getById(id: Int): Person1
    fun getAll(): List<Person1>
    fun print()
}

interface Logger {

    fun logAll()
    fun print()
}

class Controller(
    repository: Repository,
    private val logger: Logger
) : Repository by repository, Logger by logger {

    //override to solve conflict
    override fun print() {
        return logger.print()
    }
}

fun use(controller: Controller) {
    val person = controller.getById(1)
    val list = controller.getAll()
    controller.logAll()
}
