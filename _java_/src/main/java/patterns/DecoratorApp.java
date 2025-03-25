package patterns;

public class DecoratorApp {
    public static void main(String[] args) {
        PrinterInterface printer = new Printer("Hello");
        PrinterInterface printerDecor1 = new QuotesDecorator(printer);
        PrinterInterface printerDecor2 = new BracketDecorator(printer);
        PrinterInterface printerDecor3 = new QuotesDecorator(new BracketDecorator(printer));

        printer.print();
        System.out.println();

        printerDecor1.print();
        System.out.println();

        printerDecor2.print();
        System.out.println();

        printerDecor3.print();

    }
}
//------------------------------------Components----------------------------------

//Component или Decorator (Wrapper)
interface PrinterInterface {
    void print();
}

//ConcreteComponent
class Printer implements PrinterInterface {
    String value;

    public Printer(String value) {
        this.value = value;
    }

    public void print() {
        System.out.print(value);
    }
}


//------------------------------------Decorators----------------------------------

//Decorator
abstract class Decorator implements PrinterInterface {
    private PrinterInterface component;

    public Decorator(PrinterInterface component) {
        this.component = component;
    }

    public void print() {
        component.print();
    }
}

//ConcreteDecorator 1
class QuotesDecorator extends Decorator {
    public QuotesDecorator(PrinterInterface component) {
        super(component);
    }

    public void print() {
        System.out.print("\"");
        super.print();
        System.out.print("\"");
    }
}

//ConcreteDecorator 2
class BracketDecorator extends Decorator {
    public BracketDecorator(PrinterInterface component) {
        super(component);
    }

    public void print() {
        System.out.print("[");
        super.print();
        System.out.print("]");
    }
}
