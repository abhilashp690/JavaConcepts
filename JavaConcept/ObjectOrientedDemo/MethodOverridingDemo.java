package JavaConcept.ObjectOrientedDemo;

class BaseClass{
    public void print(){
        System.out.println("Base Class Printed...");
    }

    public static void IamStatic() {
        System.out.println("Static I am parent ");
    }
}

public class MethodOverridingDemo extends BaseClass{
    @Override
    public void print() {
        System.out.println("Child Class Printed....");
    }

    public void childName() {
        System.out.println("Child class name is printed now");
    }

    public static void IamStatic() {
        System.out.println("Static I am child ");
    }

    public static void main(String[] args) {
        BaseClass obj = new BaseClass();
        obj.print();

        BaseClass obj1 = new MethodOverridingDemo();
        obj1.print();

        MethodOverridingDemo obj2 = new MethodOverridingDemo();
        obj2.childName();

        MethodOverridingDemo.IamStatic();
        BaseClass.IamStatic();

        obj1.IamStatic();
        obj2.IamStatic();
    }

}
