package patterns

object ProxyCompositionApp {

    @JvmStatic
    fun main(args: Array<String>) {
        //с помощью прокси реализуем ленивую загрузку изображения
        val image: Image = ProxyImage("D:/images/my.jpg")
        println("создан но не загружен")
        //объект RealImage создается только когда вызывается метод display()
        image.display()
    }

    //-------------------------------Subjects----------------------------------
    //Subject
    interface Image {

        //request
        fun display()
    }

    //ConcreteSubject
    class RealImage(var file: String) : Image {

        init {
            load()
        }

        fun load() {
            println("Загрузка $file")
        }

        override fun display() {
            println("Просмотр $file")
        }
    }

    //-------------------------------Proxy----------------------------------
    class ProxyImage(var file: String) : Image {

        var image: RealImage? = null

        override fun display() {
            if (image == null) {
                image = RealImage(file)
            }
            image!!.display()
        }
    }
}
