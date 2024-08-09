package patterns;

public class BridgeApp {

	public static void main(String[] args) {
		Carr c = new Sedan(new Kia());
		c.showDetails();

		Carr c2 = new Hatchback(new Kia());
		c2.showDetails();

		Carr c3 = new Sedan(new Skoda());
		c3.showDetails();

		Carr c4 = new Coupe(new Mercedes());
		c4.showDetails();
	}
}

//--------------------------------Abstraction---------------------------------------
abstract class Carr{
	Make make;
	public Carr(Make m){make=m;}

	//operation
	abstract void showType();

	void showDetails(){
		showType();
		make.showMake();
		System.out.println();
	}
}

class Sedan extends Carr{
	public Sedan(Make m) {super(m);}
	void showType() {
		System.out.println("Sedan");
	}
}

class Hatchback extends Carr{
	public Hatchback(Make m) {super(m);}
	void showType() {
		System.out.println("Hatchback");
	}
}

class Coupe extends Carr{
	public Coupe(Make m) {super(m);}
	void showType() {
		System.out.println("Coupe");
	}
}

//--------------------------------Implementor---------------------------------------
interface Make{
	//operation
	void showMake();
}

//ConcreteImplementor 1
class Kia implements Make{
	public void showMake() {System.out.println("Kia");}
}

//ConcreteImplementor 2
class Skoda implements Make{
	public void showMake() {System.out.println("Skoda");}
}

//ConcreteImplementor 3
class Mercedes implements Make{
	public void showMake() {System.out.println("Mercedes");}
}
