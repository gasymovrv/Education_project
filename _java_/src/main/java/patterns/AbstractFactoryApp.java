package patterns;

public class AbstractFactoryApp {

    public static void main(String[] args) {
        DeviceFactory factory = DeviceFactory.getFactoryByCountryCode("RU");

        Mouse m = factory.getMouse();
        Keyboard k = factory.getKeyboard();
        Touchpad t = factory.getTouchpad();

        m.click();
        k.print();
        t.track(10, 35);
    }
}

//-------------------------------abstract products----------------------------------
interface Mouse {
    void click();
}

interface Keyboard {
    void print();
}

interface Touchpad {
    void track(int deltaX, int deltaY);
}

//-------------------------------concrete products 1----------------------------------
class RuMouse implements Mouse {
    public void click() {
        System.out.println("Щелчок мышью");
    }
}

class RuKeyboard implements Keyboard {
    public void print() {
        System.out.print("Печатаем строку");
    }
}

class RuTouchpad implements Touchpad {
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Передвинулись на " + s + " пикселей");
    }
}

//-------------------------------concrete products 2----------------------------------
class EnMouse implements Mouse {
    public void click() {
        System.out.println("Mouse click");
    }
}

class EnKeyboard implements Keyboard {
    public void print() {
        System.out.print("Print");
    }
}

class EnTouchpad implements Touchpad {
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Moved " + s + " pixels");
    }
}

//-------------------------------abstract factory----------------------------------
interface DeviceFactory {
    Mouse getMouse();

    Keyboard getKeyboard();

    Touchpad getTouchpad();

    static DeviceFactory getFactoryByCountryCode(String lang) {
        switch (lang) {
            case "RU":
                return new RuDeviceFactory();
            case "EN":
                return new EnDeviceFactory();
            default:
                throw new RuntimeException("Unsupported Country Code: " + lang);
        }
    }
}

//-------------------------------concrete factories----------------------------------
class EnDeviceFactory implements DeviceFactory {
    public Mouse getMouse() {
        return new EnMouse();
    }

    public Keyboard getKeyboard() {
        return new EnKeyboard();
    }

    public Touchpad getTouchpad() {
        return new EnTouchpad();
    }
}

class RuDeviceFactory implements DeviceFactory {
    public Mouse getMouse() {
        return new RuMouse();
    }

    public Keyboard getKeyboard() {
        return new RuKeyboard();
    }

    public Touchpad getTouchpad() {
        return new RuTouchpad();
    }
}
