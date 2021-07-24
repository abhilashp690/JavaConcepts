package JavaConcept.ThreadingConcepts.SynchronizationConstructs;


import java.util.concurrent.Phaser;

class FileReaderDemo implements Runnable {

    private String threadName;
    private String fileName;
    private Phaser ph;

    public FileReaderDemo(String threadName, String fileName, Phaser ph) {
        this.threadName = threadName;
        this.fileName = fileName;
        this.ph = ph;
        this.ph.register();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Reading File - " + fileName + " , by Thread - " + threadName + " , current phase is - " + ph.getPhase() + " , Arrived Parties - " + ph.getArrivedParties() + " , unarrived Parties - " + ph.getUnarrivedParties() + " , Registered Parties - " + ph.getRegisteredParties());
            ph.arriveAndAwaitAdvance();
            ph.arriveAndDeregister();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class DBReaderDemo implements Runnable {

    private String threadName;
    private String dbName;
    private Phaser ph;

    public DBReaderDemo(String threadName, String dbName, Phaser ph) {
        this.threadName = threadName;
        this.dbName = dbName;
        this.ph = ph;
        this.ph.register();
        new Thread(this::run);
    }

    @Override
    public void run() {
        System.out.println("DB Reader - " + dbName + " , by Thread - " + threadName + " , current phase is - " + ph.getPhase() + " , Arrived Parties - " + ph.getArrivedParties() + " , unarrived Parties - "+ph.getUnarrivedParties() + " , Registered Parties - "+ph.getRegisteredParties());
        ph.arriveAndAwaitAdvance();
        ph.arriveAndDeregister();
    }
}


public class PhaserDemonstration {
    public static void main(String[] args) {
        System.out.println("This is Phaser Demonstration.....");
        Phaser ph = new Phaser();
        ph.register();
        System.out.println("Initial Phase Number is - " + ph.getPhase());
        new Thread(new FileReaderDemo("Thread1" , "file1" ,ph)).start();
        new Thread(new FileReaderDemo("Thread2" , "file2" ,ph)).start();
        new Thread(new FileReaderDemo("Thread3" , "file3" ,ph)).start();

        System.out.println("Main Thread is also awaiting for next phase...");
        ph.arriveAndAwaitAdvance();
        System.out.println("Arrived Parties - " + ph.getArrivedParties() + " , unarrived Parties - "+ph.getUnarrivedParties() + " , Registered Parties - "+ph.getRegisteredParties());

        System.out.println("Current Phase Number is - " + ph.getPhase());
        new Thread(new DBReaderDemo("Thread4" , "mysql" , ph)).start();
        new Thread(new DBReaderDemo("Thread5" , "postgres" , ph)).start();
        new Thread(new DBReaderDemo("Thread6" , "oracle" , ph)).start();
        ph.arriveAndAwaitAdvance();
    }
}
