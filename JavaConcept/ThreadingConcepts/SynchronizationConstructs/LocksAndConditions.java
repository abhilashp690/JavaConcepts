package JavaConcept.ThreadingConcepts.SynchronizationConstructs;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProducerCondition implements Runnable{
    Condition condition;
    BlockingQueue<Integer> queue;
    Lock lock;
    public ProducerCondition(Condition condition , BlockingQueue queue , Lock lock){
        this.condition = condition;
        this.queue = queue;
        this.lock = lock;
    }

    @Override
    public void run() {
        while(true){
        try{
            lock.lock();
            while(queue.size() == 5){
                System.out.println("Queue is full , Producer is in wait state now");
                condition.awaitUninterruptibly();
            }
            int data = new Random(100).nextInt();
            System.out.println("Produced the item :- " + data);
            queue.add(data);
            Thread.sleep(1000);
            condition.signalAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        }
    }
}

class ConsumerCondition implements Runnable{
    Condition condition;
    BlockingQueue queue;
    Lock lock;
    public ConsumerCondition(Condition condition , BlockingQueue queue , Lock lock){
        this.condition = condition;
        this.queue = queue;
        this.lock = lock;
    }

    @Override
    public void run() {
        while(true){
            try{
                lock.lock();
                while(queue.isEmpty()){
                    System.out.println("Queue is Empty , Consumer is in wait state now .....");
                    condition.awaitUninterruptibly();
                }

                Integer data = (Integer) queue.poll();
                System.out.println("Consumer consumed data - " + data);
                Thread.sleep(1000);
                condition.signalAll();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }
}

public class LocksAndConditions {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition ctx = lock.newCondition();

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        ProducerCondition producer = new ProducerCondition(ctx , queue , lock);
        ConsumerCondition consumer = new ConsumerCondition(ctx , queue , lock);

        new Thread(consumer).start();
        new Thread(producer).start();

    }
}
