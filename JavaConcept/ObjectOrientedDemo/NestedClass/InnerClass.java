package JavaConcept.ObjectOrientedDemo.NestedClass;


class OuterClass {
    private int privateNo = 10;
    protected int protectedNo = 20;

    public OuterClass(){
        System.out.println("Outer Class Instance Triggered....");
    }

    class InnerClass{
        public void displayOuterData() {
            System.out.println("Parent Class Data :- " + privateNo + " : " + protectedNo);
        }

        public void privateMethodTriggered() {
            System.out.println("Inner Class Triggered ...");
        }
    }
}

public class InnerClass {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.displayOuterData();
        inner.privateMethodTriggered();
    }
}
