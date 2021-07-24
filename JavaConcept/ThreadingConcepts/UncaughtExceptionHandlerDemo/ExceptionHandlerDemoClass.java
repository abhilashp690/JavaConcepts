package JavaConcept.ThreadingConcepts.UncaughtExceptionHandlerDemo;

class CustomTask implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(Integer.parseInt("1"));
        System.out.println(Integer.parseInt("2"));
        System.out.println(Integer.parseInt("ss"));
        System.out.println(Integer.parseInt("4"));
        System.out.println(Integer.parseInt("5"));
    }
}

public class ExceptionHandlerDemoClass {
    public static void main(String[] args) {
        new Thread(new CustomTask()).start();
    }
}
