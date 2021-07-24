package JavaConcept.CollectionsDemonstration;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        System.out.println("SET INTERFACE -> COLLECTION INTERFACE -> ITERABLE INTERFACE ...");
        System.out.println("DIFFERENT TYPES OF SUPPORTED SET ARE :-");
        System.out.println("1].HashSet\n2].LinkedHashSet\n3].TreeSet");


        /////////////////////////HashSet////////////////////////////////////
        System.out.println("\n\nHash Set Demonstration....");
        System.out.println("Hash Set does not maintains insertion order and no duplicates are allowed.");
        System.out.println("Hash Set is not synchronized.");

        Set<String> hashSet = new HashSet<>();
        hashSet.add("two");
        hashSet.add("three");
        hashSet.add("one");
        hashSet.add("one");
        hashSet.add(null);
        hashSet.add("one");


        try {
            Iterator<String> hSetItr = hashSet.iterator();
            while (hSetItr.hasNext()) {
                System.out.println("Key is - " + hSetItr.next());
                hashSet.add("four");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught.");
            System.out.println("******HashSet iterator is fail fast.");
        }



        ///////////////////////////LinkedHashSet///////////////////////////////////////////////
        System.out.println("\n\nLinkedHashSet Implementation ....");
        System.out.println("LinkedHashSet maintains insertion order and no duplicates are allowed.");
        System.out.println("LinkedHashSet is not synchronized.");
        System.out.println("Internally maintains double linked list.");

        Set<String> lList = new LinkedHashSet<>();
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
            System.out.println("******Linked HashSet iterator is fail fast.");
        }



        ////////////////////////////////TreeSet//////////////////////////////////////////////
        System.out.println("\n\nTreeSet Demonstration....");
        System.out.println("TreeSet maintains sorted order and no duplicates are allowed.");
        System.out.println("TreeSet is not synchronized.");
        System.out.println("TreeSet internally uses Red-black tree");
        System.out.println("TreeSet does not allow null key.");
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("one");
        treeSet.add("two");
        treeSet.add("one");
        treeSet.add("one");
        treeSet.add("one");
        treeSet.add("three");


        try {
            Iterator<String> treeSetItr = treeSet.iterator();
            while (treeSetItr.hasNext()) {
                System.out.println("Key is - " + treeSetItr.next());
                treeSet.add("four");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught.");
            System.out.println("******TreeSet iterator is fail fast.");
        }



        System.out.println("\nFINAL SUMMARY FOR ALL SET :- ");
        System.out.println("All Set iterator are fail-fast");
        System.out.println("NEED TO MAINTAIN INSERTION ORDER - LINKEDHASHSet");
        System.out.println("NEED TO SORT BASED ON KEYS - TREESET");
    }
}
