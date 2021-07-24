package JavaConcept.ThreadingConcepts.SynchronizationConstructs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Job2 implements Runnable{
    Semaphore semaphore;

    CustomSemaphore customSemaphore;

    public Job2(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public Job2(CustomSemaphore customSemaphore){
        this.customSemaphore = customSemaphore;
    }

    @Override
    public void run() {
        try {
            //semaphore.acquire();
            customSemaphore.acquire();
            System.out.println("Currently Executing Thread - " + Thread.currentThread().getName());
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        finally {
            customSemaphore.release();
            //semaphore.release();
        }
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Semaphore is basically used when we want to restrict the concurrent access to the resource.");

        //Semaphore semaphore = new Semaphore(3);
        CustomSemaphore customSemaphore = new CustomSemaphore(2);

        ExecutorService es = Executors.newFixedThreadPool(3);
        //Job2 j2 = new Job2(semaphore);
        Job2 j2 = new Job2(customSemaphore);

        for(int i=0 ; i<20 ; i++)
            es.submit(j2);

        //System.out.println("Main Thread interrupting the semaphore execution.");
        //es.shutdown();
       //es.awaitTermination(10 , TimeUnit.SECONDS);
    }
}

class CustomSemaphore {
    private int value;
    public CustomSemaphore(int value) {
        this.value = value;
    }

    public synchronized void acquire() throws InterruptedException {
        while(value < 0){
            wait();
        }
        value--;
        notifyAll();
    }

    public synchronized void release() {
        value ++;
        notifyAll();
    }
}
