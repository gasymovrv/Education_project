package patterns.state;

/**
 * Реализация шаблона состояние 2
 * Переключение состояний внутри самих состояний
 */
public class StateApp2 {
	public static void main(String[] args) {
		Human human = new Human();
		human.setState(new Work());

		for (int i = 0; i < 10; i++) {
			human.doSomething();
		}		
	}
}


//-------------------------------Context-------------------------------
class Human {
	private Activity state;
	public void setState(Activity s){this.state = s;}
	//request
	public void doSomething(){
		state.doSomething(this);
	}
}


//-------------------------------States-------------------------------
interface Activity{
	//handle
	void doSomething(Human human);
}

//ConcreteState 1
class Work implements Activity{
	public void doSomething(Human context) {
		System.out.println("Работаем!!!");
		context.setState(new WeekEnd());
	}
}

//ConcreteState 2
class WeekEnd implements Activity{
	private int count = 0;
	public void doSomething(Human context) {
		if(count<3){
			System.out.println("Отдыхаем (Zzz)");
			count++;
			//context.setState(this);
		}
		else{
			context.setState(new Work());
		}
	}	
}