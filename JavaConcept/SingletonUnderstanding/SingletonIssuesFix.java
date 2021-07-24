package JavaConcept.SingletonUnderstanding;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class SingletonIssuesFix implements Serializable , Cloneable {

    private static volatile SingletonIssuesFix singleTon = null;

    private SingletonIssuesFix() {
        if(singleTon != null){
            throw new RuntimeException("Cannot create instance via reflection");
        }
        System.out.println("Creating an instance ...");
    }

    public static SingletonIssuesFix getInstance() {
        // double checking mechanism
        if(singleTon == null) {
            synchronized (SingletonIssuesFix.class) {
                if(singleTon == null)
                    singleTon = new SingletonIssuesFix();
            }
        }
        return singleTon;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning for singleton instance is not possible");
    }

    protected Object readResolve() {
        return singleTon;
    }

}

class SingletonIssuesFixedDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("This program will demonstrate how to fix the problems that breaks singleton pattern ");
        SingletonIssuesFix s1 = SingletonIssuesFix.getInstance();
        SingletonIssuesFix s2 = SingletonIssuesFix.getInstance();
        System.out.println("Hashcode :- s1 - " + s1.hashCode());
        System.out.println("Hashcode :- s2 - " + s2.hashCode());

        try {
            // Using Reflection , singleton can be broken
            System.out.println("Using Reflection , to avoid instance creation , check if instance already created , if yes then return exception");
            Class clazz = Class.forName("JavaConcept.SingletonUnderstanding.SingletonIssuesFix");
            Constructor<SingletonIssuesFix> ctor = clazz.getDeclaredConstructor();
            ctor.setAccessible(true);
            SingletonIssuesFix s3 = ctor.newInstance();
            System.out.println("Hashcode :- s3 - using reflection - " + s3.hashCode());
        }
        catch (Exception e){
            System.out.println("Cannot create instance via Reflection Exception :- " + e);
        }

        System.out.println();

        //Using Serialization/Deserialization
        // Serialized singleton Instance
        System.out.println("Using Serialization/Deserialization , need to override readResolve method that sits on top of deserialization");
        System.out.println("This readResolve method allows a class to replace/resolve the object read from the stream before it is returned to the caller , helps to control type and instances");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("a.ser")));
        oos.writeObject(s1);
        //Deserialzing singleton Instance
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(new File("a.ser")));
        SingletonIssuesFix s4 = (SingletonIssuesFix) ios.readObject();
        System.out.println("Hashcode :- s4 - using ser/deser - " + s4.hashCode());

        System.out.println();

        try {
            // Using cloning
            System.out.println("Using cloning , ideally we should throw an exception of cloningnotSupported,when clone method is triggered");
            SingletonIssuesFix s5 = (SingletonIssuesFix) s1.clone();
            System.out.println("Hashcode :- s5 - using clone - " + s5.hashCode());
        }
        catch (CloneNotSupportedException e) {
            System.out.println("Cloning is not supported - " + e);
        }

        // Some other scenarios are also there :-like multithreaded acess where multipl threads
        // requested access to getInstance() method at same time , multiple classloaders exists
        // each classloader at boot time will create own singleton instance , GC but this is not an
        // issue in latest jdk

        System.out.println();
        System.out.println("For MultiThreaded access , we should create instance only in static method getInstance with synchronized keyword to ensure multiple instances are not created");
        System.out.println("Also this will advantage in lazy loading ");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        SingletonIssuesFix s6 = executorService.submit(SingletonIssuesFix::getInstance).get();
        SingletonIssuesFix s7 = executorService.submit(SingletonIssuesFix::getInstance).get();
        System.out.println("Hashcode using multithreaded access :- s6 - " + s6.hashCode());
        System.out.println("Hashcode using multithreaded access :- s7 - " + s7.hashCode());
        executorService.shutdown();
    }
}


