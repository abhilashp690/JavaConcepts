package JavaConcept.ObjectOrientedDemo;


abstract class AbstractClass {
    public abstract void doSomeStuff();

    AbstractClass() {
        System.out.println("Abstract Class Invoked");
    }
    // Private Method cannot be abstract method as child cannot provide its implementation
    // private abstract void privateStuff();
}

public class AbstractClassDemonstration extends AbstractClass {

    public static void main(String[] args) {
        System.out.println("Main Class Triggered");

        System.out.println("Private Method cannot be abstract method as child cannot provide its implementation");
        System.out.println("Final Method cannot be abstract method as it cannot be overriden ");
        System.out.println("Constructor cannot be abstract method as it cannot be overriden ");
        new AbstractClassDemonstration().doSomeStuff();
    }

    @Override
    public void doSomeStuff() {
        System.out.println("Do some stuff triggered");
    }
}


