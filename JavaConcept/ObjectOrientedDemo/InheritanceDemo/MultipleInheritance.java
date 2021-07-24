package JavaConcept.ObjectOrientedDemo.InheritanceDemo;


interface A  {
    public void show();
    default void defaultMethod() {
        System.out.println("Default Method of INTERFACE A");
    }
}

interface B {
    public void show();
    default void defaultMethod() {
        System.out.println("Default Method of INTERFACE B");
    }
}


class C implements A , B {
    @Override
    public void show() {
        System.out.println("Class C has provided its own implementation....");
    }

    @Override
    public void defaultMethod() {
        A.super.defaultMethod();
    }
}

public class MultipleInheritance {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
        obj.defaultMethod();
    }
}
