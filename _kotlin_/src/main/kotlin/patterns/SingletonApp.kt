package patterns

object SingletonApp {

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val size = 1000
        val t = arrayOfNulls<Thread>(size)

        //создаем кучу потоков
        for (i in 0 until size) {
            t[i] = Thread(R())
        }
        //Стартуем их все (в каждом вызывается Singleton.getInstance())
        for (i in 0 until size) {
            t[i]!!.start()
        }
        //Ждем
        for (i in 0 until size) {
            t[i]!!.join()
        }
        //Проверям, что создался только один объект
        println(Singleton.counter)
    }
}

class R : Runnable {

    override fun run() {
        Singleton.instance
    }
}

class Singleton private constructor() {
    init {
        counter++
    }

    companion object {

        var counter: Int = 0

        @get:Synchronized
        @Volatile
        var instance: Singleton? = null
            get() {
                if (field == null) {
                    field = Singleton()
                }
                return field
            }
            private set
    }
}
