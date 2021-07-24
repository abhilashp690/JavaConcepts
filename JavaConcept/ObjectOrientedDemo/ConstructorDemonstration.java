package JavaConcept.ObjectOrientedDemo;

class Parent {

    public Parent() {

    }

    public Parent(int x) {
        System.out.println("I am a parent constructor . . .");
    }

    public void printMeParent() {
        System.out.println("I am Parent");
    }
}

class Child extends Parent {

    public Child(){

    }

    Child(int parentId) {
        super(parentId);
        System.out.println("HAHAHAA");
    }

    public void printMeChild() {
        System.out.println("I am Child");
    }
}

public class ConstructorDemonstration {
    public static void main(String[] args) {
        Child obj = new Child(2);
        obj.printMeChild();
        obj.printMeParent();

        System.out.println("JVM will always add a default constructor to your class if your class does not have any constructor.");
        System.out.println("If any parameterized or no-arg construcor is present then default constructor is not added.");
        System.out.println("Child Class Constructor will call base class default/no-arg constructor by default , if any construcor is not present , JVM will add it for you.");
        System.out.println("But if Parent class has parameterized constructor then child will have to explicitly call this else compile time error will be thrown");
    }
}
