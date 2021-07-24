package JavaConcept.ObjectOrientedDemo;

public class MethodOverloadingDemo {

    public void add(){
    }

    public void add(int a){
    }

    public int add(int a, int b){
        System.out.println("int");
        return -1;
    }

    public String a(String s) {
        return "1";
    }

    public int a(Integer a){
        return -1;
    }

    public void add(double a, double b){
        System.out.println("Double");
    }

    public static void main(String[] args) {
        System.out.println("Method overloading is based on 3 parameters only :- ");
        System.out.println("1].Number of arguments count is different");
        System.out.println("2].Data type of arguments is different");
        System.out.println("3].Order of data types is different");

        System.out.println("Method Overloading widening suppport :- ");
        System.out.println("byte ->  char -> short -> int -> long -> float -> double . But widening will only happen if we have corresponding primitive data types only , if we have wrappers , then compiler will throw error.");
        new MethodOverloadingDemo().add(2.5,3);
        System.out.println();
        System.out.println("Method overloading is type of static polymorphism......");

        System.out.println(new MethodOverloadingDemo().a((Integer) null));
    }
}
