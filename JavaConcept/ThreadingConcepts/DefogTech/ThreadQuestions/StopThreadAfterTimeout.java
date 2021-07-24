package JavaConcept.ThreadingConcepts.DefogTech.ThreadQuestions;

class JobTask implements Runnable{
    @Override
    public void run(){
        try {
            while (true) {
                System.out.println("Still Performing the task");
                Thread.sleep(1000);
            }
        }
        catch (Exception e){
            System.out.println(Thread.currentThread().isInterrupted());
            e.printStackTrace();
        }
    }
}

public class StopThreadAfterTimeout {
    public static void main(String[] args) {
        System.out.println("We want a thread to be terminated after 30 seconds....");

        Thread th = new Thread(new JobTask());
        th.start();

        try {
            Thread.sleep(10000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        th.interrupt();
    }
}
