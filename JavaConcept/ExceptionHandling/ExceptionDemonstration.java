package JavaConcept.ExceptionHandling;

import java.io.IOException;

public class ExceptionDemonstration {
    public static void main(String[] args) {
        System.out.println("Exception Handling Demonstration....");

    }
}

class A {
    public A(int name) {
        this.name = name;
    }

    public A() {
    }

    int name;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void printMe() throws IOException {
        throw new IOException();
    }

    @Override
    public String toString() {
        return "A{" +
                "name=" + name +
                '}';
    }
}

class B extends A{
    int id;

    public B(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printMe()  {
        throw new RuntimeException();
    }
}
