package patterns.builder;

/**
 * Реализация строителя без директора
 */
public class BuilderApp2 {

	public static void main(String[] args) {
 		Car car = new CarBuilder()
					.buildMaker("Mercedes")
					.buildTransmission(Transmission.AUTO)
					.buildMaxSpeed(280)
					.build();
		System.out.println(car);
	}
}

//ConcreteBuilder
class CarBuilder{

	private String m = "Default";
	private Transmission t = Transmission.MANUAL;
	private int s = 120;
	//buildPart
	CarBuilder buildMaker(String m){
		this.m = m;
		return this;
	}
	//buildPart
	CarBuilder buildTransmission(Transmission t){
		this.t = t;
		return this;
	}
	//buildPart
	CarBuilder buildMaxSpeed(int s){
		this.s = s;
		return this;
	}
	//getResult
	Car build(){
		Car car = new Car();
		car.setMaker(m);
		car.setTransmission(t);
		car.setMaxSpeed(s);
		return car;
	}
}
