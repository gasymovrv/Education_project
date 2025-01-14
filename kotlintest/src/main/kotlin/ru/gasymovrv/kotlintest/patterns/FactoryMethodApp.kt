package ru.gasymovrv.kotlintest.patterns

import java.time.LocalTime
import java.util.Date

object FactoryMethodApp {

    @JvmStatic
    fun main(args: Array<String>) {
        //пример 1
        var maker = WatchMaker.getMakerByName("Digital")
        var watch = maker.createWatch()
        watch.showTime()

        //пример 2
        maker = WatchMaker.getMakerByTime()
        watch = maker.createWatch()
        watch.showTime()
    }
}

//------------------------------------products----------------------------------
//abstract product
interface Watch {

    fun showTime()
}

//concrete product 1
class DigitalWatch : Watch {

    override fun showTime() {
        println("Digital time = " + Date())
    }
}

//concrete product 2
class RomeWatch : Watch {

    override fun showTime() {
        println("Rome time = VII-XX")
    }
}

//----------------------------------factories------------------------------------
//abstract factory
interface WatchMaker {

    fun createWatch(): Watch

    companion object {

        fun getMakerByName(maker: String): WatchMaker {
            if (maker == "Digital") return DigitalWatchMaker()
            else if (maker == "Rome") return RomeWatchMaker()
            throw RuntimeException("Не поддерживаемая производственная линия часов: $maker")
        }

        /**
         * Выдает фабрику DigitalWatchMaker, если сейчас на часах меньше 12:00,
         * иначе - RomeWatchMaker
         * @return Реализации интерфейса WatchMaker
         */
        fun getMakerByTime(): WatchMaker {
            return if (LocalTime.now().isBefore(LocalTime.of(12, 0)))
                DigitalWatchMaker()
            else
                RomeWatchMaker()
        }
    }
}

//concrete factory 1
class DigitalWatchMaker : WatchMaker {

    override fun createWatch(): Watch {
        return DigitalWatch()
    }
}

//concrete factory 2
class RomeWatchMaker : WatchMaker {

    override fun createWatch(): Watch {
        return RomeWatch()
    }
}

// Фаб.метод можно запихнуть и в такой урезанный вариант, но это не красиво и плохо расширяется
//class WatchMaker {
//	public Watch createWatch(String maker) {
//		if (maker.equals("Digital"))
//			return new DigitalWatch();
//		else if (maker.equals("Rome"))
//			return new RomeWatch();
//		throw new RuntimeException("Не поддерживаемая производственная линия часов: " + maker);
//	}
//}



