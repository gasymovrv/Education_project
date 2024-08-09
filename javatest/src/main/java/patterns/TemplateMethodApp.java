package patterns;
public class TemplateMethodApp {
	public static void main(String[] args) {
		Abstr a = new A();
		a.templateMethod();

		System.out.println();
		
		Abstr b = new B();
		b.templateMethod();
	}
}

//AbstractClass
abstract class Abstr {
	final void templateMethod(){
		System.out.print("1");
		subMethod1();
		System.out.print("3");
		subMethod2();
	}
	abstract void subMethod1();
	abstract void subMethod2();
}

//ConcreteClass 1
class A extends Abstr {
	void subMethod1(){
		System.out.print("2");
	}
	void subMethod2() {
		System.out.print("4");
	}
}

//ConcreteClass 2
class B extends Abstr {
	void subMethod1(){
		System.out.print("-------");
	}
	void subMethod2() {}
}