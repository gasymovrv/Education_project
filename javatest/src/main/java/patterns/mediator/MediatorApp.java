package patterns.mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorApp {
	public static void main(String[] args) {

		TextChat chat = new TextChat();
		
		User admin = new Admin(chat, "Руслан");
		User u1 = new SimpleUser(chat,"Ваня");
		User u2 = new SimpleUser(chat, "Вова");
		User u3 = new SimpleUser(chat, "Ахмед");
		
		chat.addUser(admin);
		chat.addUser(u1);
		chat.addUser(u2);
		chat.addUser(u3);
		
		admin.outbox("Привет");
		System.out.println();
		u1.outbox("Хай");
		System.out.println();
		u2.outbox("Прив");
		System.out.println();
		u3.outbox("Салам");
	}
}

//--------------------------------------Component или Colleague----------------------------------

//Component или Colleague
abstract class User{
	Chat chat;
	String name;

	public User(Chat chat, String name) {this.chat = chat;this.name=name;}
	
	public String getName() {return name;}

	//компонент отправляет сообщение посреднику
	public void outbox(String message) {
		chat.sendMessage(message, this);
	}
	//компонент получает сообщение от посредника
	abstract void inbox(String message);
}

//ConcreteComponent или ConcreteColleague
class Admin extends User{
	public Admin(Chat chat, String name) {super(chat,name);}
	
	public void inbox(String message) {
		System.out.println("Администратор " + getName()+" получает сообщение '"+ message + "'");
	}
}

//ConcreteComponent или ConcreteColleague
class SimpleUser extends User{
	public SimpleUser(Chat chat, String name) {super(chat, name);}
	
	public void inbox(String message) {
		System.out.println("Пользователь " + getName()+" получает сообщение '"+ message + "'");
	}
}

//--------------------------------------------Mediator----------------------------------------

//Mediator
interface Chat{
	void sendMessage(String message, User user);
}

//ConcreteMediator
class TextChat implements Chat{
	//clients
	private Admin admin;
	private List<SimpleUser> users = new ArrayList<>();

	public void addUser(User u){
		if(u instanceof SimpleUser){
			users.add((SimpleUser)u);
		}
		else if(u instanceof Admin && admin==null){
			this.admin = (Admin)u;
		}
		else{
			throw new RuntimeException("Админ уже есть");
		}
	}
	
	public void sendMessage(String message, User user) {
		//транслируем сообщения от user всем кроме user
		if(user instanceof Admin){
			for(User u :  users){
				u.inbox(user.getName()+"(Админ): "+message);
			}			
		}
		if(user instanceof SimpleUser){
			for(User u :  users){
				if(u!=user)
					u.inbox(user.getName()+": "+message);
			}
			admin.inbox(user.getName()+": "+message);
		}
	}
	
}