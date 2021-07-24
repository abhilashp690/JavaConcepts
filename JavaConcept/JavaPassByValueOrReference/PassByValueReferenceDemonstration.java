package JavaConcept.JavaPassByValueOrReference;

import java.util.HashMap;

class Ballon {
    private String color;

    public Ballon(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Ballon{" +
                "color='" + color + '\'' +
                '}';
    }
}

public class PassByValueReferenceDemonstration {
    public static void main(String[] args) {
        System.out.println("So Java is always pass by value.In case of pass by value , copy of original data is created and then it is passed.In case of pass by reference , swap operation would have swapped both the objects.");
        Ballon blue = new Ballon("Blue");
        Ballon red = new Ballon("Red");

        System.out.println("HashCode for blue object - " + System.identityHashCode(blue));
        System.out.println("HashCode for red object - " + System.identityHashCode(red));

        swap(blue , red);

        System.out.println("After swap operation , blue ballon = " + blue);
        System.out.println("After swap operation , red ballon = " + red);

        changeOriginalColor(blue , red);
        System.out.println("After swap operation , blue ballon = " + blue);
        System.out.println("After swap operation , red ballon = " + red);

        System.out.println("Varargs Demonstration.....");
        varArgDemonstrateMethod("A","B","C","D");
    }

    private static void varArgDemonstrateMethod(String ...stringArgs) {
        for(String s : stringArgs)
            System.out.print(s + "\t");
    }

    private static void changeOriginalColor(Ballon blue, Ballon red) {
        blue.setColor(red.getColor());
        red = new Ballon("Green");
    }

    private static void swap(Object blue, Object red) {
        Object temp = blue;
        blue = red;
        red = temp;
    }
}
