package patterns;
public class IteratorApp {
	public static void main(String[] args) {
		Tasks c = new Tasks();
		
		Iterator it = c.getIterator();
		
		while(it.hasNext()){
			System.out.println((String)it.next());
		}
	}
}

//Iterator
interface Iterator{
	boolean hasNext();
	Object next();
}

//Aggregate
interface Container{
	Iterator getIterator();
}

//ConcreteAggregate
class Tasks implements Container{
	private String[] tasks = {"Построить дом", "Вырастить сына", "Посадить дерево"};

	@Override
	public Iterator getIterator() {
		return new TaskIterator();
	}

	//ConcreteIterator (у стандартных коллекций также реализовано внутренним классом)
	private class TaskIterator implements Iterator{
		int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < tasks.length;
		}

		@Override
		public Object next() {
			return tasks[index++];
		}
	}
}