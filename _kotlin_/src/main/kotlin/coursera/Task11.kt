package coursera

import coursera.extensions.eq

// Implement the function that builds a sequence of Fibonacci numbers using 'sequence' function.
// Use 'yield'.
fun fibonacci(): Sequence<Int> = sequence {
    var elements = 0 to 1
    while (true) {
        yield(elements.first)
        elements = Pair(elements.second, elements.first + elements.second)
    }
}

fun main(args: Array<String>) {
    fibonacci().take(4).toList().toString() eq
        "[0, 1, 1, 2]"

    fibonacci().take(10).toList().toString() eq
        "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
}
