package JavaConcept.ObjectOrientedDemo.InheritanceDemo;

class Parent {
    int parentId = 10;
    int todaysDay = 5;
    public void myId() {
        System.out.println("Parent Id is " + parentId + " , Todays day is " + todaysDay);
    }

    public void parentMethodOnly() {
        System.out.println("Parent Method called");
    }
}


class Child1 extends Parent{
    int childId = 20;
    int todaysDay = 8;
    public void myId() {
        parentMethodOnly();
        System.out.println("Base class todays day is :- " + super.todaysDay);
        System.out.println("Child Id is " + childId + " , Todays day is " + todaysDay);
        super.parentMethodOnly();
    }
}

class Child2 extends Child1{
    int childId2 = 20;

    public void getMeId() {
        System.out.println("Base class parent Id is " + parentId);
        super.myId();
    }
}

public class MultiLevelInheritance {
    public static void main(String[] args) {
        Child2 child = new Child2();
        child.getMeId();
    }
}
