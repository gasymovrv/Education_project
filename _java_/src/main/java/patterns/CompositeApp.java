package patterns;

import java.util.ArrayList;
import java.util.List;

public class CompositeApp {

    public static void main(String[] args) {
        Shape square1 = new Squuare();
        Shape square2 = new Squuare();
        Shape triangle1 = new Triaangle();

        Shape square3 = new Squuare();
        Shape circle1 = new Cicrle();
        Shape circle2 = new Cicrle();
        Shape circle3 = new Cicrle();

        Composite composit = new Composite();
        Composite composit1 = new Composite();
        Composite composit2 = new Composite();

        composit1.addComponent(square1);
        composit1.addComponent(square2);
        composit1.addComponent(triangle1);

        composit2.addComponent(square3);
        composit2.addComponent(circle1);
        composit2.addComponent(circle2);
        composit2.addComponent(circle3);

        composit.addComponent(composit1);
        composit.addComponent(composit2);
        composit.addComponent(new Triaangle());

        //Работаем со всем деревом как с одним объектом
        composit.draw();
    }
}

//-------------------------------Components----------------------------------

//Component
interface Shape {
    //operation
    void draw();
}

//ConcreteComponent 1
class Squuare implements Shape {
    public void draw() {
        System.out.println("Привет, я Квадрат.");
    }
}

//ConcreteComponent 2
class Triaangle implements Shape {
    public void draw() {
        System.out.println("Привет, я Треугольник.");
    }
}

//ConcreteComponent 3
class Cicrle implements Shape {
    public void draw() {
        System.out.println("Привет, я Круг.");
    }
}

//-------------------------------Composite----------------------------------
class Composite implements Shape {
    private List<Shape> components = new ArrayList<>();

    public void addComponent(Shape component) {
        components.add(component);
    }

    public void removeComponent(Shape component) {
        components.remove(component);
    }

    //operation
    public void draw() {
        for (Shape component : components) {
            component.draw();
        }
    }
}

