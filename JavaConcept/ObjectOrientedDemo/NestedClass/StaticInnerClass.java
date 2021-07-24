package JavaConcept.ObjectOrientedDemo.NestedClass;

class OuterClass1{
    private static int privateNo = 10 ;
    protected int protectedNo = 20;

    static class InnerClass1 {
        public void printOuterClassData(){
            System.out.println("Outer Class Data is :- " + privateNo);
        }

        public void printStaticInnerClass() {
            System.out.println("Static Method of inner Class .... ");
        }
    }
}


public class StaticInnerClass {
    public static void main(String[] args) {
        OuterClass1 outer = new OuterClass1();
        OuterClass1.InnerClass1 inner = new OuterClass1.InnerClass1();
        inner.printOuterClassData();
        inner.printStaticInnerClass();
    }
}
