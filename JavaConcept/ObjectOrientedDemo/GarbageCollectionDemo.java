package JavaConcept.ObjectOrientedDemo;

public class GarbageCollectionDemo{
    public static void main(String args[]) throws InterruptedException {
        /* Here we are intentionally assigning a null
         * value to a reference so that the object becomes
         * non reachable
         */
        GarbageCollectionDemo obj=new GarbageCollectionDemo();
        obj=null;

        /* Here we are intentionally assigning reference a
         * to the another reference b to make the object referenced
         * by b unusable.
         */
        System.gc();
        Thread.sleep(3000);
        GarbageCollectionDemo a = new GarbageCollectionDemo();
        GarbageCollectionDemo b = new GarbageCollectionDemo();
        b = a;
        System.gc();
    }
    protected void finalize() throws Throwable
    {
        System.out.println("Garbage collection is performed by JVM");
    }
}