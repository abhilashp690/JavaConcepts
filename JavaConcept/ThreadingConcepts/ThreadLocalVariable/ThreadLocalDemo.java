package JavaConcept.ThreadingConcepts.ThreadLocalVariable;

import java.util.concurrent.atomic.AtomicInteger;

class MyOwnJob implements Runnable{

    volatile int jobId;
    AtomicInteger totalTransactionCount = new AtomicInteger(0);

    static ThreadLocal<Integer> transactionId = new ThreadLocal<Integer>()
    {
        @Override
        protected Integer initialValue()
        {
            return 1;
        }
    };

    public MyOwnJob(int jobId){
        this.jobId = jobId;
    }

    @Override
    public void run() {
        totalTransactionCount.getAndIncrement();
        transactionId.set(totalTransactionCount.get());
        try {
            TransactionService.logTransaction(jobId);
            TransactionService.executeTransaction(jobId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Removing transaction id for Thread " + Thread.currentThread().getName() +
                    " , value of transaction id was - " + MyOwnJob.transactionId.get() + " , job id is " + jobId);
            transactionId.remove();
        }
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        MyOwnJob job1 = new MyOwnJob(1);
        MyOwnJob job2 = new MyOwnJob(2);

        for(int i = 0 ; i<4 ; i ++) {
            new Thread(job1).start();
            new Thread(job2).start();
        }
    }
}
