package patterns

object ChainApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val logger0: Logger = SMSLogger(Level.ERROR) //в смс пишем только error (самое важное)
        val logger1: Logger = FileLogger(Level.DEBUG) //в файл пишем debug и важнее
        val logger2: Logger = EmailLogger(Level.INFO) //в email пишем все
        //создаем цепочку
        logger0.setNext(logger1).setNext(logger2)

        logger0.writeMessage("Все хорошо", Level.INFO)
        println()
        logger0.writeMessage("идет режим отладки", Level.DEBUG)
        println()
        logger0.writeMessage("Система упала", Level.ERROR)
    }
}

//Handler
abstract class Logger(private val priority: Int) {

    private var next: Logger? = null

    fun setNext(next: Logger): Logger {
        this.next = next
        return next
    }

    fun writeMessage(message: String, level: Int) {
        if (level <= priority) {
            print(message)
        }
        if (next != null) {
            next!!.writeMessage(message, level)
        }
    }

    abstract fun print(message: String)
}

//ConcreteHandler 1
class SMSLogger(priority: Int) : Logger(priority) {

    override fun print(message: String) {
        println("СМС: $message")
    }
}

//ConcreteHandler 2
class FileLogger(priority: Int) : Logger(priority) {

    override fun print(message: String) {
        println("Записываем в файл: $message")
    }
}

//ConcreteHandler 3
class EmailLogger(priority: Int) : Logger(priority) {

    override fun print(message: String) {
        println("E-mail сообщение: $message")
    }
}

//приоритет ошибки (меньше - важнее)
object Level {

    const val ERROR: Int = 1
    const val DEBUG: Int = 2
    const val INFO: Int = 3
}
