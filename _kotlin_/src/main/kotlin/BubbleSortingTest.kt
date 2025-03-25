fun main() {
    println(
        arrayOf("s", "a", "c", "d", "b")
            .doBubbleSort().contentToString()
    )
    println(
        arrayOf(3, 5, 2, 8, 6, 9, 1, 7, 4)
            .doBubbleSort().contentToString()
    )
    println(
        arrayOf('3', 'a', '5', '2', '8', 'b', '6', '4', '7', '9', '1')
            .doBubbleSort().contentToString()
    )
}

fun <T : Comparable<T>> Array<T>.doBubbleSort(): Array<T> {
    for (i in this.indices) {
        for (j in (this.size - 1) downTo i) {
            if (this[i] > this[j]) {
                val temp = this[i]
                this[i] = this[j]
                this[j] = temp
            }
        }
    }
    return this
}
