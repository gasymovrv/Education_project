package patterns;

import java.util.ArrayList;
import java.util.List;

public class MementoGame {
	public static void main(String[] args) {
		File file = new File();
		Game game = new Game();

		game.set("LVL_1", 30000);
		System.out.print(game);
		file.addSave(game.save());
		System.out.println(" - Сохранились");
		
		game.set("LVL_2", 55000);
		System.out.println(game);

		game.set("LVL_3", 80000);
		System.out.print(game);
		file.addSave(game.save());
		System.out.println(" - Сохранились");

		game.set("LVL_4", 120000);
		System.out.println(game);

		System.out.println();
		
		System.out.println("загружаемся из 1го сохранения");
		game.load(file.getSave(0));
		System.out.println(game);

		System.out.println("загружаемся из последнего сохранения");
		game.load(file.getLastSave());
		System.out.println(game);
	}
}

//Originator (объект, состояние которого будем сохранять в Memento)
class Game{
	//state
	private String level;
	private int ms;
	public void set(String level, int ms){this.level=level; this.ms=ms;}

	//setMemento
	public void load(Save save){
		level = save.getLevel();
		ms = save.getMs();
	}
	//createMemento
	public Save save(){
		return new Save(level, ms);
	}

	@Override
	public String toString() {
		return "Game [level=" + level + ", ms=" + ms + "]";
	}
}

//Memento
class Save{
	//state
	private final String level;
	private final int ms;

	public Save(String level, int ms) {
		this.level = level;
		this.ms = ms;
	}
	public String getLevel() {return level;}
	public int getMs() {return ms;}
}

//Caretaker
class File{
	private List<Save> save = new ArrayList<>();
	public Save getLastSave() {return save.get(save.size()-1);}
	public Save getSave(int i) {return save.get(i);}
	public void addSave(Save save) {this.save.add(save);}

}