package JavaConcept.SingletonUnderstanding;

import java.io.*;
import java.lang.reflect.Constructor;

public class SingleTonPatternProblems implements Serializable , Cloneable {

    private static final SingleTonPatternProblems singleTon = new SingleTonPatternProblems();

    private SingleTonPatternProblems() {
        System.out.println("Creating an instance ...");
    }

    public static SingleTonPatternProblems getInstance() {
        return singleTon;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class SingletonDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("This program will demonstrate the problems that breaks singleton pattern ");
        SingleTonPatternProblems s1 = SingleTonPatternProblems.getInstance();
        SingleTonPatternProblems s2 = SingleTonPatternProblems.getInstance();
        System.out.println("Hashcode :- s1 - " + s1.hashCode());
        System.out.println("Hashcode :- s2 - " + s2.hashCode());

        // Using Reflection , singleton can be broken
        System.out.println("Using Reflection");
        Class clazz = Class.forName("JavaConcept.SingletonUnderstanding.SingleTonPatternProblems");
        Constructor<SingleTonPatternProblems> ctor =  clazz.getDeclaredConstructor();
        ctor.setAccessible(true);
        SingleTonPatternProblems s3 = ctor.newInstance();
        System.out.println("Hashcode :- s3 - using reflection - "+ s3.hashCode());

        //Using Serialization/Deserialization
        // Serialized singleton Instance
        System.out.println("Using Serialization/Deserialization");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("a.ser")));
        oos.writeObject(s1);
        //Deserialzing singleton Instance
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(new File("a.ser")));
        SingleTonPatternProblems s4 = (SingleTonPatternProblems) ios.readObject();
        System.out.println("Hashcode :- s4 - using ser/deser - " + s4.hashCode());


        // Using cloning
        System.out.println("Using cloning");
        SingleTonPatternProblems s5 = (SingleTonPatternProblems) s1.clone();
        System.out.println("Hashcode :- s5 - using clone - " + s5.hashCode());

        // Some other scenarios are also there :-like multithreaded acess where multiple threads
        // requested access to getInstance() method at same time , multiple classloaders exists
        // each classloader at boot time will create own singleton instance , GC but this is not an
        // issue in latest jdk
    }
}
