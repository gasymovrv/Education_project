package examples.multithreading.volatile_;

import java.util.Scanner;

public class VolatileTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        //Изменение переменной running в потоке main
        myThread.shutdown();
    }
}

class MyThread extends Thread {
    private volatile boolean running = true;

    public void run() {
        //Чтение переменной running в потоке MyThread, он может ее сохранить в свой кэш процессора если не указать volatile
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}