package JavaConcept.CollectionsDemonstration;

import java.util.Objects;

public class HashMapImplementation {
    public static void main(String[] args) {
        System.out.println("Custom HashMap Implementation.....");

        CustomHashMapImplementation<Employee,Integer> myMap = new CustomHashMapImplementation<>(10);
        Employee e1 = new Employee(1 , "Abhilash1" , "Pune1");
        Employee e2 = new Employee(2 , "Abhilash2" , "Pune2");
        Employee e3 = new Employee(13 , "Abhilash3" , "Pune3");
        Employee e4 = new Employee(4 , "Abhilash4" , "Pune4");
        Employee e5 = new Employee(11 , "Abhilash5" , "Pune5");
        Employee e6 = new Employee(12 , "Abhilash6" , "Pune6");
        Employee e7 = new Employee(21 , "Abhilash7" , "Pune7");
        Employee e8 = new Employee(22 , "Abhilash8" , "Pune8");
        Employee e9 = new Employee(9 , "Abhilash9" , "Pune9");
        Employee e10 = new Employee(10 , "Abhilash10" , "Pune10");
        Employee e11 = new Employee(112 , "Abhilash4" , "Pune4");
        Employee e12 = new Employee(51 , "Abhilash5" , "Pune5");
        Employee e13 = new Employee(42 , "Abhilash6" , "Pune6");
        Employee e14 = new Employee(31 , "Abhilash7" , "Pune7");
        Employee e15 = new Employee(32 , "Abhilash8" , "Pune8");

        myMap.put(e1 , 1000);
        myMap.put(e2 , 2000);   myMap.put(e3 , 5000);
        myMap.put(e4 , 3000);   myMap.put(e5 , 1000);
        myMap.put(e6 , 15000);  myMap.put(e7 , 13000);
        myMap.put(e8 , 12000);  myMap.put(e9 , 25000);
        myMap.put(e10 , 3000);  myMap.put(e11 , 1000);
        myMap.put(e12 , 15000); myMap.put(e13 , 13000);
        myMap.put(e14 , 12000); myMap.put(e15 , 25000);
        myMap.put(e9 , 100000); myMap.put(null , 55555);

        System.out.println("Retrieved Value = " + myMap.get(new Employee(31 , "Abhilash7" , "Pune7")));
        System.out.println("Retrieved Value = " + myMap.get(null));


    }
}

class CustomHashMapImplementation <K ,V> {
    transient Entry[] customMap;

    private int initialCapacity = 16;

    private static class Entry<K,V> {
        private K key;
        private V value;
        private int hashCode;
        private Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }

    public CustomHashMapImplementation() {
        this.customMap = new Entry[initialCapacity];
    }

    public CustomHashMapImplementation(int capacity) {
        this.customMap = new Entry[capacity];
    }

    public V get(K key) {

        int hashValue = 0 , keyHashCode = 0;
        if(key != null){
            hashValue = getBucketHash(key);
            keyHashCode = key.hashCode();
        }

        if(customMap[hashValue] == null)
            return null;

        Entry<K,V> entry = customMap[hashValue];
        while (entry != null) {
            if(entry.getHashCode() == keyHashCode && (key == entry.getKey() || key.equals(entry.getKey())))
                return entry.getValue();
            entry = entry.next;
        }
        return null;
    }

    public void put(K key , V value) {
        int hashValue = 0;
        int keyHashCode = 0;
        if(key != null) {
            hashValue = getBucketHash(key);
            keyHashCode = key.hashCode();
        }

        if(customMap[hashValue] == null){
            Entry<K , V> entry = new Entry<>(key,value);
            entry.hashCode = keyHashCode;
            customMap[hashValue] = entry;
        }
        else {
            Entry<K,V> curr = customMap[hashValue];
            Entry<K,V> prev = curr;
            while (curr!= null){
                if(curr.getHashCode() == keyHashCode && (key == curr.getKey() || key.equals(curr.getKey()))) {
                    V currValue = curr.getValue();
                    curr.setValue(value);
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            Entry<K , V> entry = new Entry<>(key,value);
            entry.hashCode = keyHashCode;
            prev.next = entry;
        }
    }

    public int getBucketHash(K key) {
        return Math.abs(key.hashCode())%customMap.length;
    }
}

class Employee {
    int empId;
    String empName;
    String empAddr;

    public Employee(int empId, String empName, String empAddr) {
        this.empId = empId;
        this.empName = empName;
        this.empAddr = empAddr;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId && empName.equals(employee.empName) && empAddr.equals(employee.empAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empName, empAddr);
    }


}