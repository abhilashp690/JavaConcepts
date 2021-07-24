package JavaConcept.ThreadingConcepts.SynchronizationConstructs;

import java.util.concurrent.*;

class JOB4 implements Runnable {

    java.util.concurrent.CyclicBarrier barrier;

    public JOB4(java.util.concurrent.CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println("Current Executing Thread - " + Thread.currentThread().getName());
            Thread.sleep(2000);
            barrier.await();
            System.out.println("All have reached common point , sleeping now");
            Thread.sleep(1000);
            System.out.println("Barrier has been broken , as all threads have reached a common point");
        }catch (InterruptedException | BrokenBarrierException ex){
            ex.printStackTrace();
        }
    }

}

public class CyclicBarrierDemonstration {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Cyclic Barrier Demo");
        System.out.println("Cyclic Barrier is used when we want to ensure that all threads reach a common point and then continue further by breaking barrier.");

        ExecutorService es = Executors.newFixedThreadPool(5);
        java.util.concurrent.CyclicBarrier barrier = new java.util.concurrent.CyclicBarrier(5);
        JOB4 job4 = new JOB4(barrier);
        for(int i = 0 ; i <5 ; i++){
            es.submit(job4);
        }

        System.out.println("Current Barrier Count - " + barrier.getNumberWaiting() + " , is broken - " + barrier.isBroken());
        es.shutdown();
        es.awaitTermination(5 , TimeUnit.SECONDS);
    }
}
