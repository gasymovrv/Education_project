package patterns;
public class CommandApp {
	public static void main(String[] args) {
		Comp comp = new Comp();
		User u = new User(new StartCommand(comp), new StopCommand(comp), new ResetCommand(comp));
		
		u.startComputer();
		u.stopComputer();
		u.resetComputer();
	}
}


//Invoker (Вызыватель)
class User{
	private Command start;
	private Command stop;
	private Command reset;
	public User(Command start, Command stop, Command reset) {
		this.start = start;
		this.stop = stop;
		this.reset = reset;
	}
	void startComputer(){
		start.execute();
	}
	void stopComputer(){
		stop.execute();
	}
	void resetComputer(){
		reset.execute();
	}
}


//Reciver (Получатель - объект, к которому и будут обращаться все команды)
class Comp{
	void start(){
		System.out.println("Start");
	}
	void stop(){
		System.out.println("Stop");
	}
	void reset(){
		System.out.println("Reset");
	}
}


//Command
abstract class Command{
	Comp computer;
	public Command(Comp computer) {this.computer = computer;}
	abstract void execute();
}
//ConcreteCommand 1
class StartCommand extends Command{
	public StartCommand(Comp computer) {super(computer);}
	@Override
	public void execute() {
		computer.start();
	}
}
//ConcreteCommand 2
class StopCommand extends Command{
	public StopCommand(Comp computer) {super(computer);}
	@Override
	public void execute() {
		computer.stop();
	}
}
//ConcreteCommand 3
class ResetCommand extends Command{
	public ResetCommand(Comp computer) {super(computer);}
	@Override
	public void execute() {
		computer.reset();
	}
}