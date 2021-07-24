package JavaConcept.CollectionsDemonstration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapsDemo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("HashTable Implementation ....");
        System.out.println("Hashtable does not allow null key/value.It will throw null pointer exception");

        /////////////////////////HashTable////////////////////////////////////////
        Hashtable<String, String> hTable = new Hashtable<String, String>();
        try {
            hTable.put("one", "one");
            hTable.put("two", "two");
            hTable.put("three" , null);
        }
        catch (NullPointerException npe){
            System.out.println("Null Pointer Exception caught as null key/value is not allowed ...");
            System.out.println("HashTable Contents - using entry-set");
            for(Map.Entry<String , String> entry : hTable.entrySet()){
                System.out.println("Key - " + entry.getKey() + " value is - " + entry.getValue());
            }

            System.out.println("HashTable Contents - using iterator");
            Enumeration<String> hit = hTable.elements();
            while (hit.hasMoreElements()){
                String key = hit.nextElement();
                System.out.println("Key is - " + key + " value is - " + hTable.get(key));
                hTable.put("four" , "four");
            }
        }
        System.out.println("HashTable is slow as all its method(get , put , contains ) are synchronized and insertion order is not maintained .");
        System.out.println("Note :- Enumerator will not throw concurrent modification exception if while accessing element of map , any other thread except iterator itself modifies the collection.");
        System.out.println("***** Enumerator of hashtable is fail-safe.");



        /////////////////////////////////HASHMAP//////////////////////////////////////////
        System.out.println("\n\nHashMap Implementation ....");
        System.out.println("HashMap is not synchronized , it will provide inconsistent output when multiple threads access it.");
        System.out.println("HashMap allows any no of null values and only one null key stored at bucket 0.");
        System.out.println("Insertion order is not maintained in case of hashmap also just like hashtable.");
        Map<String,String> hMap = new HashMap<>();
        try{
            hMap.put("one", "one");
            hMap.put("two", "two");
            hMap.put("three" , null);
            hMap.put(null , "four");

            System.out.println("HashMap Contents using entryset - ");
            for(Map.Entry<String,String> entry : hMap.entrySet()){
                System.out.println("Key is - " + entry.getKey() + " Value is - " + entry.getValue());
                hMap.remove(entry.getKey());
            }
        }
        catch(ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught as map was modified when it was read by other thread.");
            System.out.println("HashMap Contents using Iterator - ");
            Iterator<String> hmapItr = hMap.keySet().iterator();
            while (hmapItr.hasNext()) {
                String key = hmapItr.next();
                System.out.println("Key is - " + key + " Value is - " + hMap.get(key));
                hmapItr.remove();
            }
            System.out.println("******Iterator of hashmap is fail-fast , as it throws error when collection is modified except by iterator itself.");
        }
        /////////////////////////////////////LINKEDHASHMAP/////////////////////////////////////////////////

        System.out.println("\n\nLinkedHashMap Implementation ....");
        System.out.println("LinkedHashmap maintains the insertion order , internally uses doubly linked list.");
        Map<String , String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("one" , "one");
        linkedHashMap.put("two" , "two");
        linkedHashMap.put("three" , "three");

        Iterator<String> linkedHashMapItr = linkedHashMap.keySet().iterator();
        try {
            while (linkedHashMapItr.hasNext()) {
                String key = linkedHashMapItr.next();
                System.out.println("Key - " + key + " value is - " + linkedHashMap.get(key));
                linkedHashMap.put("four", "four");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification Exception caught ...");
            System.out.println("***** Iterator of linkedhashmap is fail fast.Main advantage is insertion order is maintained.");
        }

        //////////////////////////////////////////TREEMAP///////////////////////////////////////////////
        System.out.println("\n\nTreeMap Implementation ....");
        System.out.println("TreeMap sorts the map based on the key.");
        System.out.println("TreeMap will require the objects used as key to implement Comparable interface , otherwise it throws classcastexception.");
        System.out.println("TreeMap internally implements sortedmap and navigablemap interface.Internally uses Red-black tree");

        Map<String , String> treeMap = new TreeMap<>();
        treeMap.put("z" , "z");
        treeMap.put("a" , "a");
        treeMap.put("s" , "s");

        Iterator<String> treeMapItr = treeMap.keySet().iterator();
        try {
            while (treeMapItr.hasNext()) {
                String key = treeMapItr.next();
                System.out.println("Key is - " + key + " value is - " + treeMap.get(key));
                treeMap.put("d", "d");
            }
        }
        catch (ConcurrentModificationException e){
            System.out.println("Concurrent Modification Exception caught ...");
            System.out.println("***** Iterator of treeMap is fail fast.Main advantage of using treemap is sorting is achieved on keys.");
        }


        ////////////////////////////IdentityHashMap//////////////////////////////////////
        System.out.println("\n\nIdentity Hashmap Implementation ....");
        System.out.println("Identity Hashmap is same as hashmap . only difference being where hashmap uses equals operator for key comparison , identityhashmap uses '=='.");
        System.out.println("IdentityHashmap is used for deep cloning/serialization . For interned strings , use identityhashmap as it saves space .");
        System.out.println("Since identityhashmap uses '==' operator for key comparisons , String s = 'test' and String s1 = new String('test') will be two different keys.");
        Map<String , String> iMap = new IdentityHashMap<>();
        iMap.put("one" , "one");
        iMap.put(new String("one"), "one");
        Iterator<String> iMapitr = iMap.keySet().iterator();
        try {
            while (iMapitr.hasNext()) {
                String key = iMapitr.next();
                System.out.println("Key is - " + key + " value is - " + iMap.get(key));
                key = iMapitr.next();
                System.out.println("Key is - " + key + " value is - " + iMap.get(key));
                iMap.put("two", "two");
            }
        }
        catch (ConcurrentModificationException e){
            System.out.println("Concurrent Modification Exception caught ...");
            System.out.println("***** Iterator of IdentityHashMap is fail fast");
        }

        ////////////////////////////WEAKHASHMAP///////////////////////////////////////////////
        System.out.println("\n\nWeak HashMap Demonstration ....");
        System.out.println("Weakhashmap is generally used for cache , where keys that are used if are of type weak reference,they are free to be garbage collected.");

        class Order {
            int orderId;
            String orderName;

            public Order(int orderId , String orderName){
                this.orderId = orderId;
                this.orderName = orderName;
            }

            @Override
            public String toString() {
                return "Order{" +
                        "orderId=" + orderId +
                        ", orderName='" + orderName + '\'' +
                        '}';
            }
        }
        Map<Order , String> wMap = new WeakHashMap<>();
        wMap.put(new Order(1,"order1") , "30");
        wMap.put(new Order(2,"order2") , "40");
        wMap.put(null , "60");

        Order ord = new Order(4 , "order4");
        wMap.put(ord , "50");
        Order ord1 = new Order(5 , "order5");
        wMap.put(ord1 , "60");

        System.out.println("WeakHashMap size is - " + wMap.size());
        System.gc();
        System.out.println("Since GC is currently running weak references will be removed frm the map.");
        Thread.sleep(2000);
        System.out.println("WeakHashMap size is - " + wMap.size());
        try{
            Iterator<Order> wMapItr = wMap.keySet().iterator();
            while (wMapItr.hasNext()){
                Order obj = wMapItr.next();
                System.out.println("Key is - " + obj + " , Value is - " + wMap.get(obj));
                wMap.put(new Order(6 ,"order6") , "70");
            }
        }
        catch (ConcurrentModificationException ex){
            System.out.println("Concurrent Modification exception caught.");
            System.out.println("***** WeakHashMap is fail fast iterator");
        }


        ///////////////////////////////CONCURRENTHASHMAP/////////////////////////////////////////
        System.out.println("\n\nConcurrent HashMap Demonstration .....");
        System.out.println("ConcurrentHashMap does not lock whole table when put request is made.Only relevant segment is locked.");
        System.out.println("In case of get request,lock is not held at all , so concurrentHashMap gives hashMap benefits faster access and synchronization benefit like hashTable");
        System.out.println("Concurrent HashMap operations are not atomic , so total safety is not guaranteed in case of multiThread environment.");

        Map<String , String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("one" , "one");
        concurrentMap.put("two" , "two");
        concurrentMap.put("three" , "three");

        Iterator<String> cMapItr = concurrentMap.keySet().iterator();
        while (cMapItr.hasNext()){
            String key = cMapItr.next();
            System.out.println("Key - " + key + " value - " + concurrentMap.get(key));
            concurrentMap.put("four" , "four");
        }
        System.out.println("****** Concurrent HashMap Iterator is fail safe iterator.");

        ///////////////////////////////////CONCURRENTSKIPLISTMAP////////////////////////////////////////////////////
        System.out.println("\n\nConcurrent Skip List Map Demonstration ... ");
        System.out.println("Concurrent Skip List map is equivalent to treemap with concurrency like concurrenthashmap.[ConcurrentNavigableMap + SortedMap]");
        System.out.println("Concurrent Skip List ensures every operation will average take O(logN) time complexity only.");

        Map<String , String> concurrentSkipList = new ConcurrentSkipListMap<>();
        concurrentSkipList.put("three" , "three");
        concurrentSkipList.put("one" , "one");
        concurrentSkipList.put("boy" , "boy");
        concurrentSkipList.put("coy" , "coy");

        Iterator<String> cSkipItr = concurrentSkipList.keySet().iterator();
        while (cSkipItr.hasNext()){
            String key = cSkipItr.next();
            System.out.println("Key - " + key + " value - " + concurrentSkipList.get(key));
            concurrentSkipList.put("four" , "four");
        }
        System.out.println("****** Concurrent SkipList Map Iterator is fail safe iterator.");

        System.out.println("---------------------------------------------------------------------------------");

        System.out.println("FINAL SUMMARY FOR ALL MAPS :- ");
        System.out.println("FAIL SAFE ITERATORS - CONCURRENTHASHMAP , CONCURRENTSKIPLISTMAP , ENUMMAP");
        System.out.println("FAIL FAST ITERATORS - HASHMAP , WEAKHASHMAP , IDENTITYHASHMAP,TREEMAP ,LINKEDHASHMAP");

        System.out.println("WHEN TO USE WHICH - ");
        System.out.println("NEED TO MAINTAIN INSERTION ORDER - LINKEDHASHMAP");
        System.out.println("NEED TO SORT BASED ON KEYS - TREEMAP / CONCURRENTSKIPLIST");
        System.out.println("NEED TO IMPLEMENT CACHE RELATED OPERATIONS - WEAKHASHMAP ");
        System.out.println("NEED TO ENSURE SYNCHRONIZATION WITH FASTER ACCESS - CONCURRENTHASHMAP");
    }
}
