package JavaConcept.Enums;

import java.io.Closeable;
import java.io.IOException;
import java.util.EnumSet;

public class EnumDemonstration {
    public static void main(String[] args) {
        System.out.println(ThreadStates.START.getPriority() + ":" + ThreadStates.START.name() + ":" + ThreadStates.START.ordinal());
        System.out.println(ThreadStates.RUNNING.getPriority() + ":" + ThreadStates.RUNNING.name() + ":" + ThreadStates.RUNNING.ordinal());
        System.out.println(ThreadStates.RUNNABLE.getPriority() + ":" + ThreadStates.RUNNABLE.name() + ":" + ThreadStates.RUNNABLE.ordinal());
        System.out.println(ThreadStates.BLOCKED.getPriority() + ":" + ThreadStates.BLOCKED.name() + ":" + ThreadStates.BLOCKED.ordinal());

        System.out.println("\nIndividual Enum getDetails");
        ThreadStates.START.getDetails();
        ThreadStates.RUNNABLE.getDetails();
        ThreadStates.RUNNING.getDetails();
        ThreadStates.BLOCKED.getDetails();

        System.out.println("\nIndividual Enum toString");
        System.out.println(ThreadStates.START.toString());
        System.out.println(ThreadStates.RUNNING.toString());
        System.out.println(ThreadStates.RUNNABLE.toString());
        System.out.println(ThreadStates.BLOCKED.toString());

        System.out.println("\nNow we will see Thread State , how to iterate over all enums");
        System.out.println("1].Using EnumSet");
        enumSetIteration();

        System.out.println("\n2].Using EnumValues");
        enumValuesIteration();

        System.out.println("\n2].Using EnumValueOf");
        ThreadStates thState = Enum.valueOf(ThreadStates.class , "START");
        System.out.println(thState.name()+":"+ thState.getPriority()+":"+ thState.ordinal());
    }

    private static void enumValuesIteration() {
        ThreadStates[] threadStates = ThreadStates.values();
        for(int i=0; i < threadStates.length ; i++){
            System.out.println("Enum Details - " + threadStates[i].name()+":"+threadStates[i].ordinal()+":"+threadStates[i].getPriority());
        }
    }

    private static void enumSetIteration() {
        EnumSet<ThreadStates> enumSet = EnumSet.allOf(ThreadStates.class);
        for(ThreadStates th : enumSet){
            System.out.println("Enum Details - " + th.name()+":"+th.ordinal()+":"+th.getPriority());
        }
    }
}

enum ThreadStates implements Closeable {
    START(10) {
        @Override
        void getDetails() {
            System.out.println("START TRIGGERED");
        }

        @Override
        public String toString() {
            return ("Custom to String - " + getPriority());
        }
    },
    RUNNING(20){
        @Override
        void getDetails() {
            System.out.println("RUNNING STATE");
        }
    },
    RUNNABLE(30){
        @Override
        void getDetails() {
            System.out.println("RUNNABLE STATE");
        }
    },
    BLOCKED(40){
        @Override
        void getDetails() {
            System.out.println("BLOCKED STATE");
        }
    };

    private int priority;

    private ThreadStates(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    abstract void getDetails();

    @Override
    public void close() throws IOException {
        System.out.println("Close Method Of Enum");
    }

    @Override
    public String toString() {
        return this.name()+" priority is :- " + this.priority;
    }
}
