package JavaConcept.ThreadingConcepts.BasicMultiThreading;

class JoinDemo1 extends Thread{
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread - " + Thread.currentThread().getName() + " : " + i);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class JoinDemo2 extends Thread{
    public void run() {
        try {
            for (int i = 11; i < 20; i++) {
                System.out.println("Thread - " + Thread.currentThread().getName() + " : " + i);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class YeildJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        JoinDemo1 jd1 = new JoinDemo1();
        JoinDemo2 jd2 = new JoinDemo2();
        jd2.start();
        jd2.join();
        jd1.start();
        jd1.join();

        System.out.println("----------------------------SLEEP DEMO EXAMPLE-------------------------------");

        JoinDemo1 jd3 = new JoinDemo1();
        JoinDemo2 jd4 = new JoinDemo2();
        jd3.setPriority(1);
        jd4.setPriority(10);
        jd3.start();
        jd4.start();
        jd3.yield();
    }
}


