package patterns

object FacadeApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val computer = Computer()
        //один простой метод из фасада вместо вызова кучи разных сервисов в клиентском коде
        computer.copy()
    }
}

//--------------------------------------Фасад------------------------------------------------
class Computer {

    private val power = Power()
    private val dvd = DVDRom()
    private val hdd = HDD()

    fun copy() {
        power.on()
        dvd.load()
        hdd.copyFromDVD(dvd)
    }
}

//----------------------------Внутренние сервисы фасада---------------------------------------
class Power {

    fun on() {
        println("Включение питания")
    }

    fun off() {
        println("Выключение питания")
    }
}

class DVDRom {

    private var data = false
    fun hasData(): Boolean {
        return data
    }

    fun load() {
        data = true
    }

    fun unload() {
        data = false
    }
}

class HDD {

    fun copyFromDVD(dvd: DVDRom) {
        if (dvd.hasData()) {
            println("Происходит копирование данных с диска")
        } else {
            println("Вставьте диск с данными")
        }
    }
}
