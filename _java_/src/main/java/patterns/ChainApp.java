package patterns;

public class ChainApp {
    public static void main(String[] args) {
        Logger logger0 = new SMSLogger(Level.ERROR);//в смс пишем только error (самое важное)
        Logger logger1 = new FileLogger(Level.DEBUG);//в файл пишем debug и важнее
        Logger logger2 = new EmailLogger(Level.INFO);//в email пишем все
        //создаем цепочку
        logger0.setNext(logger1).setNext(logger2);

        logger0.writeMessage("Все хорошо", Level.INFO);
        System.out.println();
        logger0.writeMessage("идет режим отладки", Level.DEBUG);
        System.out.println();
        logger0.writeMessage("Система упала", Level.ERROR);
    }
}

//приоритет ошибки (меньше - важнее)
class Level {
    public static final int ERROR = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
}


//Handler
abstract class Logger {
    private int priority;
    private Logger next;

    public Logger(int priority) {
        this.priority = priority;
    }

    public Logger setNext(Logger next) {
        this.next = next;
        return next;
    }

    public void writeMessage(String message, int level) {
        if (level <= priority) {
            print(message);
        }
        if (next != null) {
            next.writeMessage(message, level);
        }
    }

    abstract void print(String message);
}

//ConcreteHandler 1
class SMSLogger extends Logger {
    public SMSLogger(int priority) {
        super(priority);
    }

    public void print(String message) {
        System.out.println("СМС: " + message);
    }
}

//ConcreteHandler 2
class FileLogger extends Logger {
    public FileLogger(int priority) {
        super(priority);
    }

    public void print(String message) {
        System.out.println("Записываем в файл: " + message);
    }

}

//ConcreteHandler 3
class EmailLogger extends Logger {
    public EmailLogger(int priority) {
        super(priority);
    }

    public void print(String message) {
        System.out.println("E-mail сообщение: " + message);
    }
}

