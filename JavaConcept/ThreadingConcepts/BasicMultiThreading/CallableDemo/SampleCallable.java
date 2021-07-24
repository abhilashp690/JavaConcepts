package JavaConcept.ThreadingConcepts.BasicMultiThreading.CallableDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SampleCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException , TimeoutException {

        ExecutorService ex = Executors.newFixedThreadPool(5);
        try {
            List<Future<String>> futureList = new ArrayList<Future<String>>();


            for (int i = 0; i < 30; i++) {
                CbDemo cb = new CbDemo();
                Future<String> fut = ex.submit(cb);
                futureList.add(fut);
            }

            futureList.get(0).cancel(false);

            //Thread.currentThread().interrupt();
            for (Future<String> fut : futureList) {
                System.out.println(fut.isDone() + ":" + fut.isCancelled());
                System.out.println(fut.get());
            }
        }
        finally {
            ex.shutdownNow();
        }
    }
}

class CbDemo implements Callable<String> {

    static int i = 0;

    @Override
    public String call() throws IOException,Exception {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append(i++).append(" ").append(",");
            }
//            throw new IOException("some io is missing....");
            Thread.sleep(5000);
            return sb.toString();
        }
}
