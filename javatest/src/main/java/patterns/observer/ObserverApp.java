package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverApp {
	public static void main(String[] args) {
		MeteoStation station = new MeteoStation();

		//можем добавлять и удалять наблюдателей (подписывать и отписывать)
		station.addObserver(new ConsoleObserver());
		station.addObserver(new FileObserver());
		
		station.setMeasurements(25, 760);
		System.out.println();
		station.setMeasurements(-5, 745);

	}
}

//--------------------------------Subject (Издательство)--------------------------------
interface Subject {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
//ConcreteSubject
class MeteoStation implements Subject {
	//subjectState
	private int temperature;//1.Наблюдаемый имеет и меняет свое состояние и посылает это изменение своим подписчикам,
	private int pressure;//а посредник(медиатор) нет
	private List<Observer> observers = new ArrayList<>();//2. имеет коллекцию подписчиков и не делает различий между ними

	public void addObserver(Observer o) {
		observers.add(o);
	}
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers() {
		for(Observer o: observers){
			o.update(temperature, pressure);
		}
	}
	
	public void setMeasurements(int t, int p){//3. Издательство тупо рассылает свое изменившееся состояние подписчикам
		temperature = t;
		pressure = p;
		notifyObservers();
	}
}

//----------------------------------Observer (Подписчик)----------------------------------
interface Observer{
	void update(int temp, int presser);//4. Наблюдатели только получают сообщения, отправлять не могут
}
//ConcreteObserver 1
class ConsoleObserver implements Observer{
	public void update(int temp, int presser) {
		System.out.println("Консольный наблюдатель: Погода изменилась. Температура = " + temp + ", Давление = " + presser +".");
	}
}
//ConcreteObserver 2
class FileObserver implements Observer{
	public void update(int temp, int presser) {
		System.out.println("Файловый наблюдатель: Погода изменилась. Температура = " + temp + ", Давление = " + presser +".");
	}
}
