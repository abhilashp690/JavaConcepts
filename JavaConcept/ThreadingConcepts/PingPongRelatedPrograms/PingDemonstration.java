package JavaConcept.ThreadingConcepts.PingPongRelatedPrograms;

public class PingDemonstration {
    public static void main(String[] args) {
        PingPong table = new PingPong();
        Person Alice = new Person("BOB" , table);
        Person Bob = new Person("ALICE" , table);
        Thread alice = new Thread(Alice);
        Thread bob = new Thread(Bob);
        alice.setName("ALICE");
        bob.setName("BOB");

        alice.start();
        bob.start();

        try {
            Thread.sleep(6000);
        }catch (InterruptedException e){

        }
        Thread.currentThread().setName("CHUCK");
        table.hit("DONE");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e){

        }
    }
}

class PingPong{
    private String currentTurn = null;

    public synchronized boolean hit(String opponentName){
        String executingThread = Thread.currentThread().getName();

        if(currentTurn == null) {
            currentTurn = executingThread;
            return true;
        }

        if(opponentName.compareTo("DONE") == 0)
        {
            System.out.println(executingThread + " has made a request to stop processing of both players.");
            currentTurn = opponentName;
            notifyAll();
            return false;
        }

        if(currentTurn.compareTo("DONE") == 0) {
            System.out.println(executingThread + " has exited.....");
            return false;
        }

        if(currentTurn.compareTo(executingThread) == 0){
            System.out.println("Ping executed by - " + executingThread);
            currentTurn = opponentName;
            notifyAll();
        } else {
            try {
                Long t1 = System.currentTimeMillis();
                wait(2500);
                if(System.currentTimeMillis() - t1 > 2500){
                    System.out.println("Timeout occured waiting for thread - " + executingThread + " , to hit");
                }
            }
            catch (InterruptedException e){

            }
        }
        return true;
    }
}


class Person implements Runnable {

    String opponentName;
    PingPong table;

    public Person(String opponentName , PingPong table) {
        this.opponentName = opponentName;
        this.table = table;
    }

    public void run() {
        while (table.hit(opponentName)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}