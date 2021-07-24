package JavaConcept.CollectionsDemonstration;

import java.util.*;

public class ListsDemo {
    public static void main(String[] args) {
        System.out.println("LIST INTERFACE EXTENDS COLLECTION INTERFACE ...");
        System.out.println("DIFFERENT TYPES OF SUPPORTED LIST ARE :-");
        System.out.println("1].ArrayList\n2].LinkedList\n3].Vector\n4].Stack\n5].CopyonWriteArrayList(Concurrent Version of Array List)");

        Objects.hash(1,"Abhilash" , 5.6);

        /////////////////////////ARRAYLIST////////////////////////////////////
        System.out.println("\n\nArray List Demonstration....");
        System.out.println("Array List maintains insertion order and duplicates are allowed.");
        System.out.println("Array List is not synchronized.");
        System.out.println("Array List internally grows by 50% in case of rehashing.");

        List<String> aList = new ArrayList<>();
        aList.add("one");
        aList.add("one");
        aList.add(null);
        aList.add("one");
        aList.add("two");
        aList.add("three");

        try {
            Iterator<String> aListItr = aList.iterator();
            while (aListItr.hasNext()) {
                System.out.println("Key is - " + aListItr.next());
                aList.add("four");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught.");
            System.out.println("******ArrayList iterator is fail fast.");
        }



        ///////////////////////////LINKEDLIST///////////////////////////////////////////////
        System.out.println("\n\nLinkedList Implementation ....");
        System.out.println("Linked List maintains insertion order and duplicates are allowed.");
        System.out.println("Linked List is not synchronized.");
        System.out.println("Linked list does not support index based access.");
        System.out.println("Internally maintains double linked list.");
        List<String> lList = new LinkedList<>();
        lList.add("two");
        lList.add("one");
        lList.add(null);
        lList.add("one");
        lList.add("one");
        lList.add("three");

        try {
            Iterator<String> lListItr = lList.iterator();
            while (lListItr.hasNext()) {
                System.out.println("Key is - " + lListItr.next());
                lList.add("four");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught.");
            System.out.println("******Linked List iterator is fail fast.");
        }



        ////////////////////////////////VECTOR//////////////////////////////////////////////
        System.out.println("\n\nVector Demonstration....");
        System.out.println("Vector maintains insertion order and duplicates are allowed.");
        System.out.println("Vector is synchronized.");
        System.out.println("Vector internally grows by 100% in case of rehashing.");
        List<String> vector = new Vector<>();
        vector.add("one");
        vector.add("two");
        vector.add(null);
        vector.add("one");
        vector.add("one");
        vector.add("one");
        vector.add("three");


        try {
            Iterator<String> vectorItr = vector.iterator();
            while (vectorItr.hasNext()) {
                System.out.println("Key is - " + vectorItr.next());
                vector.add("four");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught.");
            System.out.println("******Vector iterator is fail fast.");
        }


        ////////////////////////////STACK EXTENDS VECTOR//////////////////////////////////////////////
        System.out.println("\n\nStack Demonstration...");
        System.out.println("Stack maintains LIFO order , Stack extends vector class");
        Stack<String> stack = new Stack();
        stack.push("ABC");
        stack.push("DEF");
        stack.push("GHI");

        while (!stack.isEmpty()) {
            System.out.println("Stack Element :- " + stack.pop());
            stack.remove("ABC");
        }

        System.out.println("---------------------------------------------------------------------------------");

        System.out.println("\nFINAL SUMMARY FOR ALL LISTS :- ");
        System.out.println("All Lists iterator are fail-fast");
        System.out.println("Synchronized Collection needed - Vector , otherwise - ArrayList,LinkedList");
        System.out.println("Use ArrayList when more/frequent search operations are needed as these are O(1)");
        System.out.println("Use LinkedList if frequent add operations are required.");
    }
}
