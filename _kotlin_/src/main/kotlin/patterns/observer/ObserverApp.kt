package patterns.observer

object ObserverApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val station = MeteoStation()

        //можем добавлять и удалять наблюдателей (подписывать и отписывать)
        station.addObserver(ConsoleObserver())
        station.addObserver(FileObserver())

        station.setMeasurements(25, 760)
        println()
        station.setMeasurements(-5, 745)
    }
}

//--------------------------------Subject (Издательство)--------------------------------
interface Subject {

    fun addObserver(o: Observer)
    fun removeObserver(o: Observer)
    fun notifyObservers()
}

//ConcreteSubject
class MeteoStation : Subject {

    //subjectState
    // 1. Наблюдаемый имеет и меняет свое состояние и посылает это изменение своим подписчикам, а посредник(медиатор) нет
    private var temperature = 0
    private var pressure = 0

    // 2. имеет коллекцию подписчиков и не делает различий между ними
    private val observers: MutableList<Observer> = ArrayList()

    override fun addObserver(o: Observer) {
        observers.add(o)
    }

    override fun removeObserver(o: Observer) {
        observers.remove(o)
    }

    override fun notifyObservers() {
        for (o in observers) {
            o.update(temperature, pressure)
        }
    }

    fun setMeasurements(
        t: Int,
        p: Int
    ) {
        // 3. Издательство тупо рассылает свое изменившееся состояние подписчикам
        temperature = t
        pressure = p
        notifyObservers()
    }
}

//----------------------------------Observer (Подписчик)----------------------------------
interface Observer {

    // 4. Наблюдатели только получают сообщения, отправлять не могут
    fun update(temp: Int, pressure: Int)
}

//ConcreteObserver 1
class ConsoleObserver : Observer {

    override fun update(temp: Int, pressure: Int) {
        println("Консольный наблюдатель: Погода изменилась. Температура = $temp, Давление = $pressure.")
    }
}

//ConcreteObserver 2
class FileObserver : Observer {

    override fun update(temp: Int, pressure: Int) {
        println("Файловый наблюдатель: Погода изменилась. Температура = $temp, Давление = $pressure.")
    }
}
