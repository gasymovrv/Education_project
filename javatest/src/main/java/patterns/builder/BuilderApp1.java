package patterns.builder;

/**
 * Реализация строителя с директором
 */
public class BuilderApp1 {

	public static void main(String[] args) {
		Director director = new Director();
		director.setBuilder(new FordMondeoBuilder());
		Car car = director.buildCar();
		System.out.println(car);
		director.setBuilder(new SubaruBuilder());
		Car car2 = director.buildCar();
		System.out.println(car2);
	}
}


//----------------------------Builder----------------------------------

//AbstractBuilder
abstract class AbstractCarBuilder {
	Car car;
	public AbstractCarBuilder() {
		car = new Car();
	}
	//buildPart
	abstract AbstractCarBuilder buildMaker();
	//buildPart
	abstract AbstractCarBuilder buildTransmission();
	//buildPart
	abstract AbstractCarBuilder buildMaxSpeed();
	//getResult
	Car getCar(){return car;}
}

//ConcreteBuilder 1
class FordMondeoBuilder extends AbstractCarBuilder {
	AbstractCarBuilder buildMaker() {car.setMaker("Ford Mondeo"); return this;}
	AbstractCarBuilder buildTransmission() {car.setTransmission(Transmission.AUTO); return this;}
	AbstractCarBuilder buildMaxSpeed() {car.setMaxSpeed(260); return this;}
}

//ConcreteBuilder 2
class SubaruBuilder extends AbstractCarBuilder {
	AbstractCarBuilder buildMaker() {car.setMaker("Subaru"); return this;}
	AbstractCarBuilder buildTransmission() {car.setTransmission(Transmission.MANUAL); return this;}
	AbstractCarBuilder buildMaxSpeed() {car.setMaxSpeed(320); return this;}
}



//----------------------------Director----------------------------------
class Director{
	private AbstractCarBuilder builder;
	void setBuilder(AbstractCarBuilder b){builder = b;}

	//construct
	Car buildCar(){
		builder.buildMaker().buildTransmission().buildMaxSpeed();
		return builder.getCar();
	}
}
