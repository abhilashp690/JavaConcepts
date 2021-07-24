package JavaConcept.ThreadingConcepts.UncaughtExceptionHandlerDemo;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Thread has abrupty stopped due to some exception. Thread id - " + t.getId() + " Thread name is :- " + t.getName());
        System.out.println("Exception Caught is :- " + e.getMessage());
        System.out.println("Will try to retry the task again ...." + " , current thread state is :- " + t.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        new Thread(new CustomTask()).start();
    }
}
