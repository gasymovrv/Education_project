package ru.gasymovrv.kotlintest.patterns

object ProxyInheritanceApp {

    @JvmStatic
    fun main(args: Array<String>) {
        //с помощью прокси реализуем ленивую загрузку изображения
        val image: RealImage = ProxyImage("D:/images/my.jpg")
        println("создан но не загружен")
        //объект RealImage создается только когда вызывается метод display()
        image.display()
    }

    //-------------------------------Subjects----------------------------------
    //ConcreteSubject
    open class RealImage(private var file: String) {

        init {
            load()
        }

        open fun load() {
            println("Загрузка $file")
        }

        open fun display() {
            println("Просмотр $file")
        }
    }

    //-------------------------------Proxy----------------------------------
    class ProxyImage(file: String) : RealImage(file) {

        private var canLoad: Boolean = false

        override fun display() {
            canLoad = true
            load()
            super.display()
        }

        override fun load() {
            if (canLoad) {
                super.load()
            }
        }
    }
}
