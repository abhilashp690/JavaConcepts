package JavaConcept.ThreadingConcepts.CustomThreadPoolFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class MyOwnThreadPool implements ThreadFactory {

    private String threadInitial;
    private List<String> threadStats;
    private volatile int counter = 0;

    public MyOwnThreadPool(String threadInitial){
        this.threadInitial = threadInitial;
        threadStats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r , threadInitial+"-"+counter);
        threadStats.add(threadInitial+"-"+counter+" , has been created");
        counter++;
        System.out.println("Request to create a thread has arrived.....");
        return t;
    }

    public void printStats() {
        StringBuilder sb = new StringBuilder();
        for(String s : threadStats){
            sb.append(s+"\n");
        }
        System.out.println("Thread Pool Stats are :- " + sb.toString());
    }
}

public class CustomThreadPoolDemo {
    public static void main(String[] args) {

        MyOwnThreadPool threadPool = new MyOwnThreadPool("CustomThreadPool");
        ExecutorService executor = Executors.newFixedThreadPool(5 , threadPool);


        for( int i =0 ;i <10 ; i++) {
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Currently Executing task " + finalI + " , thread is :- " + Thread.currentThread().getName());
                }
            });
        }
        executor.shutdown();
        threadPool.printStats();
    }
}


