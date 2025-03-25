package examples.multithreading.simple;

public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread myThread2 = new Thread(new Runner());
        myThread2.start();

        System.out.println("hello from main thread");
    }
}

class Runner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread__" + i + "__");
        }
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread__" + i + "__");
        }
    }
}
