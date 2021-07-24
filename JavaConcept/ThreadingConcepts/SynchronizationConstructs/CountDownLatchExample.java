package JavaConcept.ThreadingConcepts.SynchronizationConstructs;

import java.util.concurrent.*;

class JOB3 implements Callable<Integer>{

    CountDownLatch latch;

    public JOB3(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public Integer call() {
        try {
            System.out.println("Current Executing Thread - " + Thread.currentThread().getName() + " , current Count - " + latch.getCount());
            Thread.sleep(1000);
            latch.countDown();
            System.out.println("I have counted down - " + Thread.currentThread().getName());
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return 1;
    }
}


public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        System.out.println("Count Down Latch is used in scenarios in which we want to ensure all threads have completed their execution...");
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        JOB3 job = new JOB3(latch);

        for(int i=0 ; i < 10 ; i++){
            Future<Integer> future = executorService.submit(job);
        }

        latch.await();
        System.out.println("All Threads have completed the execution ....");
        executorService.shutdownNow();
        executorService.awaitTermination(1 , TimeUnit.MILLISECONDS);
    }
}
