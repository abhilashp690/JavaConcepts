package JavaConcept.ReflectionDemo;

import java.lang.reflect.*;

class Dog {
    public String dogName;
    private int dogAge;

    public Dog() {
        System.out.println("New Instance is created");
    }

    private Dog(int dogAge){
        System.out.println("Private COnstructor Triggered");
    }

    public Dog(String dogName , int dogAge){
        this.dogName = dogName;
        this.dogAge = dogAge;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    public void printName(){
       System.out.println("Dog Name is - "+dogName);
   }

    private void printAge(){
        System.out.println("Dog Name is - "+dogAge);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", dogAge=" + dogAge +
                '}';
    }
}

public class ReflectionDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Reflection Demontration....");
        System.out.println("Reflection is used in order to inspect/perform modification of fields/methods on runtime");

        Dog d = new Dog("BooBoo" , 10);

        Class c = d.getClass();
        System.out.println("Retrieving fields of class Dog - ");

        Field f = c.getDeclaredField("dogAge");
        f.setAccessible(true);
        f.set(d , 30);

        System.out.println();

        Field[] fields = c.getDeclaredFields();
        for(Field f1 : fields){
            f1.setAccessible(true); // This step is needed for private fields only.
            System.out.println(Modifier.toString(f1.getModifiers()) + "-" +f1.getName() + "-" + f1.get(d));
        }


        System.out.println();

        System.out.println("Retrieving methods of class Dog - ");

        Method[] methods = c.getDeclaredMethods();
        for(Method method: methods){
            System.out.println(Modifier.toString(method.getModifiers()) + "-" + method.getName());
        }

        System.out.println();

        System.out.println("Retrieving Constructor of class Dog - ");
        Constructor[] constructors = c.getDeclaredConstructors();
        for(Constructor ctr : constructors){
            ctr.setAccessible(true);
            if(ctr.getParameterCount() == 2) {
                Dog d1 = (Dog) ctr.newInstance("HAHAHA",80);
                System.out.println("Instance returned - " + d1);
            }
            System.out.println(Modifier.toString(ctr.getModifiers()) +"-" + ctr.getName()+"-"+ctr.getParameterCount());
        }
    }
}
