package JavaConcept.ThreadingConcepts.ThreadLocalVariable;

public class TransactionService {

    public static void logTransaction(int jobid) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Logging the transaction now for " + " Job id is " + jobid + " , Transaction Id is :- " + MyOwnJob.transactionId.get()
                +  " Thread Name is - " +
                Thread.currentThread().getName());
    }

    public static void executeTransaction(int jobid) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Successfully executed the transaction now.Job id is " + jobid + " , Transaction ID is " + MyOwnJob.transactionId.get() +
                 " Thread Name is - " +
                Thread.currentThread().getName());
    }

}
