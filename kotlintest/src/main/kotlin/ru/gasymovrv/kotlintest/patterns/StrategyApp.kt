package ru.gasymovrv.kotlintest.patterns


object StrategyApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val c = StrategyClient()

        val arr0 = intArrayOf(1, 3, 2, 1)
        c.setStrategy(SelectionSort())
        c.executeStrategy(arr0)

        val arr1 = intArrayOf(11, 4, 2, 7, 8, 54)
        c.setStrategy(InsertingSort())
        c.executeStrategy(arr1)

        val arr2 = intArrayOf(3, -8, 2, 0, 33, 1, 3, 2)
        c.setStrategy(BubbleSort())
        c.executeStrategy(arr2)
    }
}

//-------------------------------Context-------------------------------
class StrategyClient {

    private var strategy: Sorting? = null
    fun setStrategy(strategy: Sorting?) {
        this.strategy = strategy
    }

    fun executeStrategy(arr: IntArray) {
        strategy!!.sort(arr)
    }
}

//-------------------------------Strategies-------------------------------
//Strategy
interface Sorting {

    fun sort(arr: IntArray)
}

//Bubble sorting strategy (Сортировка пузырьком)
class BubbleSort : Sorting {

    override fun sort(arr: IntArray) {
        println("Сортировка пузырьком")
        println("до:\t" + arr.contentToString())
        for (barier in arr.indices.reversed()) {
            for (i in 0 until barier) {
                if (arr[i] > arr[i + 1]) {
                    val tmp = arr[i]
                    arr[i] = arr[i + 1]
                    arr[i + 1] = tmp
                }
            }
        }
        println("после:\t" + arr.contentToString())
    }
}

//Selection sorting strategy (Сортировка выборками)
class SelectionSort : Sorting {

    override fun sort(arr: IntArray) {
        println("Сортировка выборками")
        println("до:\t" + arr.contentToString())
        for (barier in 0 until arr.size - 1) {
            for (i in barier + 1 until arr.size) {
                if (arr[i] < arr[barier]) {
                    val tmp = arr[i]
                    arr[i] = arr[barier]
                    arr[barier] = tmp
                }
            }
        }
        println("после:\t" + arr.contentToString())
    }
}

//Inserting sorting strategy (Сортировка вставками)
class InsertingSort : Sorting {

    override fun sort(arr: IntArray) {
        println("Сортировка вставками")
        println("до:\t" + arr.contentToString())
        for (barier in 1 until arr.size) {
            var index = barier
            while (index - 1 >= 0 && arr[index] < arr[index - 1]) {
                val tmp = arr[index]
                arr[index] = arr[index - 1]
                arr[index - 1] = tmp
                index--
            }
        }
        println("после:\t" + arr.contentToString())
    }
}
