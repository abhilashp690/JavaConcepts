package JavaConcept.ThreadingConcepts.BasicMultiThreading;

import java.util.concurrent.TimeUnit;

class Task1 implements Runnable {

    static int count = 0;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Current Executing Thread :- " + Thread.currentThread().getName() + " : Value read is " + count++);
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                justCheckSynchronizationIfReentrant();
            }
        }
    }

    private synchronized void justCheckSynchronizationIfReentrant() {
            System.out.println("Reentrant in nature still executing thread :- " + Thread.currentThread().getName());
    }
}

public class SimpleJavaThreadDemo {
    public static void main(String[] args) {
        System.out.println("Total CPU Cores available :- " + Runtime.getRuntime().availableProcessors());
        Task1 task = new Task1();
        Thread t1 = new Thread(task , "First Thread");
        Thread t2 = new Thread(task , "Second Thread");
        t1.start();
        t1 = t2;
        t1.start();
    }
}
