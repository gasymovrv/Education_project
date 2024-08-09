package patterns;

public class ProxyInheritanceApp {

	public static void main(String[] args) {
		//с пмощью прокси реализуем ленивую загрузку изображения
		RealImage image = new ProxyImage("D:/images/my.jpg");
		System.out.println("создан но не загружен");
		//объект RealImage создается только когда вызывается метод display()
		image.display();
	}


//-------------------------------Subjects----------------------------------


	//ConcreteSubject
	static class RealImage {

		String file;
		public RealImage(String file) {
			this.file = file;
			load();
		}

		void load(){
			System.out.println("Загрузка " + file);
		}

		public void display() {
			System.out.println("Просмотр " + file);
		}
	}

	//-------------------------------Proxy----------------------------------
	static class ProxyImage extends RealImage{
		boolean canLoad = false;

		public ProxyImage(String file) {
			super(file);
		}

		@Override
		public void display() {
			canLoad = true;
			load();
			super.display();
		}

		@Override
		public void load(){
			if (canLoad) {
				super.load();
			}
		}
	}
}
