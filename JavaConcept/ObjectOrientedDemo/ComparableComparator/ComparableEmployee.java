package JavaConcept.ObjectOrientedDemo.ComparableComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableEmployee {
    public static void main(String[] args) {
        EmployeeComparable e1 = new EmployeeComparable("SHARVIL" , "CS3" , 3000);
        EmployeeComparable e2 = new EmployeeComparable("APURVA" , "CS1" , 2000);
        EmployeeComparable e3 = new EmployeeComparable("ABHILASH" , "CS2" , 2500);

        List<EmployeeComparable> empList = new ArrayList<EmployeeComparable>();
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);

        Collections.sort(empList);
        System.out.println(empList);
    }
}


class EmployeeComparable implements Comparable<EmployeeComparable>{
    private String name;

    private String departMent;

    private int salary;

    public EmployeeComparable(String name, String departMent, int salary) {
        this.name = name;
        this.departMent = departMent;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeComparable{" +
                "name='" + name + '\'' +
                ", departMent='" + departMent + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(EmployeeComparable emp) {
        //return this.name.compareTo(emp.name);
        return this.salary - emp.salary;
    }
}
