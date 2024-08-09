package patterns;
public class SingletonApp {
	public static void main(String[] args) throws InterruptedException {
		final int SIZE = 1000;
		Thread t[] = new Thread[SIZE];

		//создаем кучу потоков
		for(int i=0; i<SIZE; i++){
			t[i] = new Thread(new R());
		}
		//Стартуем их все (в каждом вызывается Singleton.getInstance())
		for(int i=0; i<SIZE; i++){
			t[i].start();
		}
		//Ждем
		for(int i=0; i<SIZE; i++){
			t[i].join();
		}
		//Проверям, что создался только один объект
		System.out.println(Singleton.counter);
		
	}
}
class R implements Runnable{
	@Override
	public void run() {
		Singleton.getInstance();
	}
}

class Singleton{
	public static int counter = 0;
	private static volatile Singleton instance = null;

	private Singleton(){
		counter++;
	}

	public static synchronized Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
}