package patterns

object DecoratorApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val printer: PrinterInterface = Printer("Hello")
        val printerDecor1: PrinterInterface = QuotesDecorator(printer)
        val printerDecor2: PrinterInterface = BracketDecorator(printer)
        val printerDecor3: PrinterInterface = QuotesDecorator(BracketDecorator(printer))

        printer.print()
        println()

        printerDecor1.print()
        println()

        printerDecor2.print()
        println()

        printerDecor3.print()
    }
}

//------------------------------------Components----------------------------------
//Component или Decorator (Wrapper)
interface PrinterInterface {

    fun print()
}

//ConcreteComponent
class Printer(var value: String) : PrinterInterface {

    override fun print() {
        print(value)
    }
}

//------------------------------------Decorators----------------------------------
//Decorator
abstract class Decorator(private val component: PrinterInterface) :
    PrinterInterface {

    override fun print() {
        component.print()
    }
}

//ConcreteDecorator 1
class QuotesDecorator(component: PrinterInterface) : Decorator(component) {

    override fun print() {
        print("\"")
        super.print()
        print("\"")
    }
}

//ConcreteDecorator 2
class BracketDecorator(component: PrinterInterface) : Decorator(component) {

    override fun print() {
        print("[")
        super.print()
        print("]")
    }
}
