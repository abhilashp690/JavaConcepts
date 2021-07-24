package JavaConcept.ThreadingConcepts.SynchronizationConstructs;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Job1 implements Runnable{
    static int counter = 0;
    @Override
    public void run() {
     synchronized (this){
         try {
             counter++;
             System.out.println("Current Thread - " + Thread.currentThread().getName());
             Thread.sleep(1000);
         }
         catch (InterruptedException ex){

         }
     }
    }
}

public class SynchronizedKeyword {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Synchronized keyword demonstration....");
        Job1 j1 = new Job1();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0 ; i<20 ; i++){
            es.execute(j1);
        }

        System.out.println("Current Executing Thread - " + Thread.currentThread().getName());
        Thread.sleep(3000);

        es.shutdown();
        es.awaitTermination(3 , TimeUnit.SECONDS);
        System.out.println("Shutdown will initiate shudown on active running task , along with tasks present in the queue");
        System.out.println("If we want to ensure after shutdown, actively running and queue tasks for complete execution , use awaitTermination()");
        System.out.println("Final Counter Data :- " + Job1.counter);


//        List<Runnable> notCompletedTask = es.shutdownNow();
//        System.out.println("Shutdown now does not wait for current active task to complete total execution");
//        System.out.println("Shutdown now returns list of tasks that were submittted and present in queue,but were not yet processed.");
//        System.out.println("Final Counter Data :- " + Job1.counter);
    }
}
