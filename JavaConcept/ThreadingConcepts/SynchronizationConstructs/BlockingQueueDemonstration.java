package JavaConcept.ThreadingConcepts.SynchronizationConstructs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerBlockingQueue implements Runnable{
    BlockingQueue<String> queue;
    public ProducerBlockingQueue(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("A");
            System.out.println("Produced A");
            queue.put("B");
            System.out.println("Produced B");
            queue.put("C");
            System.out.println("Produced C");
            queue.put("D");
            System.out.println("Produced D");
            queue.put("E");
            System.out.println("Produced E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ConsumerBlockingQueue implements Runnable{
    BlockingQueue<String> queue;
    public ConsumerBlockingQueue(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("Consumed " + queue.take());
            System.out.println("Consumed " + queue.take());
            System.out.println("Consumed " + queue.take());
            System.out.println("Consumed " + queue.take());
            System.out.println("Consumed " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class BlockingQueueDemonstration {
    public static void main(String[] args) {
        System.out.println("Blocking Queue Demonstration ....");
        System.out.println("Blocking Queue is a sychronized queue , that will block the producer if queue is full and will block the consumer if queue is empty.");
        System.out.println("Blocking Queue provides 2 methods for adding data in the queue :- 1].add [Will throw illegal state exception immediately if queue is full] 2]. put [Will block the producer , by adding it in wait state]");
        System.out.println("Blocking Queue provides 2 methods for removing data in the queue :- 1].remove [Will throw illegal state exception immediately if queue is empty] 2]. take [Will block the consumer , by adding it in wait state]");
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        new Thread(new ProducerBlockingQueue(queue)).start();
        new Thread(new ConsumerBlockingQueue(queue)).start();
    }
}

