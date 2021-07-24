package JavaConcept.ObjectOrientedDemo;

import java.util.ArrayList;
import java.util.List;

public class StringImutabilityBuilderBuffer {
    public static void main(String[] args) {

        String s1 = "ABC";
        String s2 = new String("ABC");
        String s7 = new String("ABC");

        String s4 = "DEF";
        String s3 = new String("DEF").intern();
        String s5 = new String("GHI");
        String s6 = "GHI";
        System.out.println("Checking if string literals with same contents are equal or not :- "  + (s1 == s2));
        System.out.println("Strings created using new keyword , not same even though same content - " + (s2 == s7));
        System.out.println("Using String intern , we can verify string using new points to string pool only - " + (s3==s4));

        System.out.println("Strings are immutable , but stringbuilder, stringbuffer are mutable");

        System.out.println("String that are created using + operator ,if both are constants(String literals)" +
                " then they will be stored in string pool , else are stored in heap");
        System.out.println("In below example , when we use s8 = (s8 + BCD) , since s8 is not literal , == evaluates to false ");

        String s8 = "A";
        s8 = s8 + "BCD";
        String s9 = "ABCD";
        System.out.println(s9 == s8);


        System.out.println("In below example , when we use s10 = (A + BCD) , since s10 is complete literal , == evaluates to true ");

        String s10 = "A" + "BCD";
        String s11 = "ABCD";
        System.out.println(s10 == s11);
        checkIfStringIsPallindrome("abcba");
        s11 = s11.replaceAll("A","");
        System.out.println(s11);

        printAllCombinationsOfString("abc");
    }

    private static void printAllCombinationsOfString(String abc) {

    }


    private static void checkIfStringIsPallindrome(String abcba) {
        int low = 0 , high=abcba.length()-1;
        while(low<high) {
            if(abcba.charAt(low) == abcba.charAt(high)){
                low ++;
                high--;
            }
            else {
                System.out.println("String is not pallindrome");
                return;
            }
        }
        System.out.println("String is pallindrome");
    }
}
