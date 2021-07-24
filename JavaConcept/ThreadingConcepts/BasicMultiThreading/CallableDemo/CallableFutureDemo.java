package JavaConcept.ThreadingConcepts.BasicMultiThreading.CallableDemo;

import java.util.Random;
import java.util.concurrent.*;

class CallbableFuture implements Callable<String> {

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i <5 ; i++)
            sb.append(new Random().nextInt());

        //This will be catched when get method is triggered as ExecutionException as call method itself has errors
        //throw new IOException("some io error occured....");
        return sb.toString();
    }
}

public class CallableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CallbableFuture cb = new CallbableFuture();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(cb);

        while (!future.isDone()) {
            System.out.println("Still computing ...");

            //Removing next line comment will give cancellation exception as you cancelled task and after loop we are calling get method
            //future.cancel(true);
        }
        System.out.println("Output is :- " + future.get(10 ,TimeUnit.MICROSECONDS));

        //This will throw timeout exception if get method cannot return output of call within specified time
        //future.get(10 ,TimeUnit.MICROSECONDS);

        // Callable returns future instance that has methods :-
        //1].get
        //2].get(duration)
        //3].cancel(boolea)
        //4].isDone()
        //5].isCancelled


        //Callable get method can throw these exceptions
        //1].Timeout exception - Get with specific time duration
        //2].Execution Exception - Call Method itself throws exception that gets wrapped inside Execution Exception
        //3].Cancellation Exception - Calling get method on future task that is already cancelled
        //4].Interrupted Exception - Thread that was sleeping , when interruped is called on it.
        executor.shutdown();
    }
}
