package patterns;

import java.time.LocalTime;
import java.util.Date;

public class FactoryMethodApp {

	public static void main(String[] args) {
		//пример 1
		WatchMaker maker = WatchMaker.getMakerByName("Digital");
		Watch watch = maker.createWatch();
		watch.showTime();

		//пример 2
		maker = WatchMaker.getMakerByTime();
		watch = maker.createWatch();
		watch.showTime();
	}
}
//------------------------------------products----------------------------------
//abstract product
interface Watch{
	void showTime();
}
//concrete product 1
class DigitalWatch implements Watch{
	public void showTime() {
		System.out.println("Digital time = "+new Date());
	}
}
//concrete product 2
class RomeWatch implements Watch{
	public void showTime() {
		System.out.println("Rome time = VII-XX");
	}
}


//----------------------------------factories------------------------------------
//abstract factory
interface WatchMaker{
	Watch createWatch();
	static WatchMaker getMakerByName(String maker){
		if(maker.equals("Digital"))
			return new DigitalWatchMaker();
		else if(maker.equals("Rome"))
			return new RomeWatchMaker();
		throw new RuntimeException("Не поддерживаемая производственная линия часов: "+ maker);
	}

	/**
	 * Выдает фабрику DigitalWatchMaker, если сейчас на часах меньше 12:00,
	 * иначе - RomeWatchMaker
	 * @return Реализации интерфейса WatchMaker
	 */
	static WatchMaker getMakerByTime(){
		if(LocalTime.now().isBefore(LocalTime.of(12, 0)))
			return new DigitalWatchMaker();
		else
			return new RomeWatchMaker();
	}
}

//concrete factory 1
class DigitalWatchMaker implements WatchMaker{
	public Watch createWatch() {
		return new DigitalWatch();
	}
}

//concrete factory 2
class RomeWatchMaker implements WatchMaker{
	public Watch createWatch() {
		return new RomeWatch();
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