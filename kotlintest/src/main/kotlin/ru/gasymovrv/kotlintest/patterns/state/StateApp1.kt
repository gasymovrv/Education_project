package ru.gasymovrv.kotlintest.patterns.state

/**
 * Реализация шаблона состояние 1
 * Переключение состояний делается контекстом
 */
object StateApp1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val radio = Radio()
        radio.setStation(RadioRecord())

        for (i in 0..9) {
            radio.play()
            radio.nextStation()
        }
    }
}

//-------------------------------Context-------------------------------
class Radio {

    private var station: Station? = null
    fun setStation(st: Station?) {
        station = st
    }

    fun nextStation() {
        if (station is RadioEurope) {
            setStation(RadioRecord())
        } else if (station is RadioRecord) {
            setStation(RadioVestiFM())
        } else if (station is RadioVestiFM) {
            setStation(RadioEurope())
        }
    }

    //request
    fun play() {
        station!!.play()
    }
}

//-------------------------------States-------------------------------
interface Station {

    //handle
    fun play()
}

//ConcreteState 1
class RadioEurope : Station {

    override fun play() {
        println("Радио \"Европа+\"...")
    }
}

//ConcreteState 2
class RadioRecord : Station {

    override fun play() {
        println("Радио \"Record\"...")
    }
}

//ConcreteState 3
class RadioVestiFM : Station {

    override fun play() {
        println("Радио \"Вести FM\"...")
    }
}
