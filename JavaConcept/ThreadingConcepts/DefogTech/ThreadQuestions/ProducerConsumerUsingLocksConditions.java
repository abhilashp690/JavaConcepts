package JavaConcept.ThreadingConcepts.DefogTech.ThreadQuestions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Producer implements Runnable{
    Lock rentrantLock;
    BlockingQueue<String>queue;
    Condition isEmpty;
    Condition isFull;

    Producer(Lock rentrantLock , BlockingQueue<String>queue , Condition isFull , Condition isEmpty){
        this.rentrantLock = rentrantLock;
        this.queue = queue;
        this.isEmpty = isEmpty;
        this.isFull = isFull;
    }

    @Override
    public void run() {
        while (true) {
            try {
                rentrantLock.lock();
                while(queue.size() == 10){
                    isEmpty.await();
                }
                System.out.println("Producer Producing message....");
                queue.put("Message Added");
                Thread.sleep(1000);
                isFull.signalAll();
            }
            catch (Exception e){e.printStackTrace();}
            finally {
                rentrantLock.unlock();
            }
        }
    }
}

class Consumer implements Runnable{
    Lock rentrantLock;
    BlockingQueue<String>queue;
    Condition isFull;
    Condition isEmpty;

    Consumer(Lock rentrantLock , BlockingQueue<String>queue , Condition isFull , Condition isEmpty){
        this.rentrantLock = rentrantLock;
        this.queue = queue;
        this.isFull = isFull;
        this.isEmpty = isEmpty;
    }

    @Override
    public void run() {
        while (true) {
           try{
               rentrantLock.lock();
               while (queue.isEmpty()){
                    isFull.await();
               }
               Thread.sleep(1000);
               String message = queue.take();
               System.out.println("Message is :- " + message);
               isEmpty.signalAll();
           }
           catch(Exception e){e.printStackTrace();}
           finally {
               rentrantLock.unlock();
           }
        }
    }
}

public class ProducerConsumerUsingLocksConditions {
    public static void main(String[] args) {
        Lock rentrantLock = new ReentrantLock();
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();

        Condition isFull = rentrantLock.newCondition();
        Condition isEmpty = rentrantLock.newCondition();

        Thread prT = new Thread(new Producer(rentrantLock , queue , isFull , isEmpty));
        Thread crT = new Thread(new Consumer(rentrantLock , queue , isFull , isEmpty));

        prT.start();
        crT.start();
    }
}
