package patterns

object CommandApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val comp = Comp()
        val u = User(StartCommand(comp), StopCommand(comp), ResetCommand(comp))

        u.startComputer()
        u.stopComputer()
        u.resetComputer()
    }
}

//Invoker (Вызыватель)
class User(
    private val start: Command,
    private val stop: Command,
    private val reset: Command
) {

    fun startComputer() {
        start.execute()
    }

    fun stopComputer() {
        stop.execute()
    }

    fun resetComputer() {
        reset.execute()
    }
}

//Reciver (Получатель - объект, к которому и будут обращаться все команды)
class Comp {

    fun start() {
        println("Start")
    }

    fun stop() {
        println("Stop")
    }

    fun reset() {
        println("Reset")
    }
}

//Command
abstract class Command(var computer: Comp) {

    abstract fun execute()
}

//ConcreteCommand 1
class StartCommand(computer: Comp) : Command(computer) {

    override fun execute() {
        computer.start()
    }
}

//ConcreteCommand 2
class StopCommand(computer: Comp) : Command(computer) {

    override fun execute() {
        computer.stop()
    }
}

//ConcreteCommand 3
class ResetCommand(computer: Comp) : Command(computer) {

    override fun execute() {
        computer.reset()
    }
}
