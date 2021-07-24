package JavaConcept.Generics;

import java.util.ArrayList;

class NonRestrictiveGenericsImpl<T>{
    T data;

    public NonRestrictiveGenericsImpl(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

class BoundedGenericsClassExtends<T extends Number>{

    T data;


    public BoundedGenericsClassExtends(T data){
        this.data = data;
    }

    public void getListContents(ArrayList<? super Integer> listData){
        System.out.println("Type is - " + listData.get(0).getClass().getName());
    }

    public T getData() {
        return data;
    }

//    public static T returnStaticData() {
//        return data;
//    }
}

public class GenericsDemonstration {
    public static void main(String[] args) {
        System.out.println("Generics Demonstration....");

        System.out.println("Advantages of Generics -");
        System.out.println("1].Code REUSABILITY - Same code can be used for all data types.");
        System.out.println("2].Compile Type Checking");
        System.out.println("3].Used in Collection Framework");
        System.out.println("GENERICS ALWAYS WORK WITH OBJECT , PRIMITIVE TYPES ARE NOT ALLOWED....");
        System.out.println("3].Generics in Collection Hierarchy...");

        System.out.println();

        System.out.println("GENERICS CAN BE OF 2 TYPES");
        System.out.println("1].Generic without Restriction Type");
        System.out.println("2].Bounded Generic with type Restriction");

        System.out.println("-------------------------------GENERIC CLASS-------------------------------------------");

        System.out.println("**********Generic without Restriction Type");
        NonRestrictiveGenericsImpl<Integer> intClass = new NonRestrictiveGenericsImpl(10);
        System.out.println("Data Returned - " + intClass.getData() + " ,Type = " + intClass.getData().getClass().getName());
        NonRestrictiveGenericsImpl<String> StringClass = new NonRestrictiveGenericsImpl("ABHILASH");
        System.out.println("Data Returned - " + StringClass.getData() + " ,Type = " + StringClass.getData().getClass().getName());


        System.out.println("**********Bounded Generic Restriction Type");
        System.out.println("1].Use of Extends , to ensure Generic class should be of type that extends class T");
        BoundedGenericsClassExtends<Integer> boundedClass = new BoundedGenericsClassExtends(1);
        System.out.println("Data Returned - " + boundedClass.getData() + " ,Type = " + boundedClass.getData().getClass().getName());
        BoundedGenericsClassExtends<Double> doubleboundedClass = new BoundedGenericsClassExtends(1.2);
        System.out.println("Data Returned - " + doubleboundedClass.getData() + " ,Type = " + doubleboundedClass.getData().getClass().getName());

        ArrayList<Object> list = new ArrayList<>();
        list.add(new Object());
        boundedClass.getListContents(list);

        System.out.println("-------------------------------COLLECTION---------------------------------------------");
        System.out.println("ArrayList with help of Generics can hold both String,Integer and any other data types");
        ArrayList<String> strList = new ArrayList<>();
        strList.add("A");
        strList.add("B");
        strList.add("C");
        System.out.println("String ArrayList - " + strList);

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        System.out.println("Integer ArrayList - " + intList);
    }
}
