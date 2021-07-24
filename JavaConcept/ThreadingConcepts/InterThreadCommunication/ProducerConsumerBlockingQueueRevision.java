package JavaConcept.ThreadingConcepts.InterThreadCommunication;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageDetails {
    int messageId;

    public MessageDetails(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "MessageDetails{" +
                "messageId=" + messageId +
                '}';
    }
}

class ProducerBQ implements Runnable {

    static int counter = 0;
    Object lock;
    BlockingQueue<MessageDetails> blockingQueue;

    public ProducerBQ(Object lock , BlockingQueue<MessageDetails> blockingQueue) {
        this.lock = lock;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println(lock);
        try {
            synchronized (lock) {
                while (true) {
                    while (blockingQueue.size() == 10) {
                        lock.wait();
                    }
                    System.out.println("Producer Initialized");
                    blockingQueue.add(new MessageDetails(counter++));
                    Thread.sleep(3000);
                    lock.notify();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


class ConsumerBQ implements Runnable {

    Object lock;
    BlockingQueue<MessageDetails> blockingQueue;

    public ConsumerBQ(Object lock , BlockingQueue<MessageDetails> blockingQueue) {
        this.lock = lock;
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
            try {
                synchronized (lock) {
                    while (true) {
                        while (blockingQueue.isEmpty()) {
                            lock.wait();
                        }
                        System.out.println("Consumer Initialized");
                        MessageDetails details = blockingQueue.poll();
                        System.out.println("Details are :- " + details);
                        Thread.sleep(3000);
                        lock.notify();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
}


public class ProducerConsumerBlockingQueueRevision {
    public static void main(String[] args) {

        Object lock = new Object();
        BlockingQueue<MessageDetails> queue = new LinkedBlockingQueue<>();

        new Thread(new ProducerBQ(lock , queue)).start();
        new Thread(new ConsumerBQ(lock , queue)).start();
    }
}
