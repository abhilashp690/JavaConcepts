package JavaConcept.ThreadingConcepts.InterThreadCommunication;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable{

    Queue<Message> messageBus = null;
    private final int totalCapacity;
    Object lock = null;
    private static int msgCounter = 0;

    public Producer(Queue<Message> messageBus , int totalCapacity , Object lock) {
        this.messageBus = messageBus;
        this.totalCapacity = totalCapacity;
        this.lock = lock;
    }

    @Override
    public void run() {
            while (true) {
                synchronized (lock) {
                try {
                    while (messageBus.size() == totalCapacity) {
                        lock.wait();
                    }
                    Message msg = new Message(msgCounter++, "Message has been published " + msgCounter);
                    messageBus.add(msg);
                    System.out.println("Prodcuer has produced the message with id " + (msgCounter-1));
                    Thread.sleep(1000);
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }
}


class Consumer implements Runnable{

    Queue<Message> messageBus = null;
    Object lock = null;

    public Consumer(Queue<Message> messageBus , Object lock) {
        this.messageBus = messageBus;
        this.lock = lock;
    }

    @Override
    public void run() {

            while (true) {
                synchronized (lock) {
                try {
                    while (messageBus.isEmpty()) {
                        lock.wait();
                    }
                    Message msg = messageBus.poll();
                    System.out.println("Consumer has received the message - Message Id - " + msg.getMessageId() + " , Message is - " + msg.getMessage());
                    Thread.sleep(1000);
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }
}

class Message {
    private int messageId;
    private String message;

    public Message(int messageId, String message) {
        this.messageId = messageId;
        this.message = message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

public class ProducerConsumerUsingWaitNotify {

    public static void main(String[] args) {
        Object lock = new Object();
        int maxQueueLength = 5;
        Queue<Message> messageBus = new LinkedBlockingQueue<>();

        Producer prod = new Producer(messageBus , maxQueueLength , lock);
        Consumer cons = new Consumer(messageBus , lock);


        Thread t1 = new Thread(prod);

        t1.getState();

        t1.start();
        new Thread(cons).start();
    }
}
