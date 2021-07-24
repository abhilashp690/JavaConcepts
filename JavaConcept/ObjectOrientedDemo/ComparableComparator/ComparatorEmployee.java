package JavaConcept.ObjectOrientedDemo.ComparableComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorEmployee {
    public static void main(String[] args) {
        EmployeeComparator e1 = new EmployeeComparator("SHARVIL" , "CS3" , 3000);
        EmployeeComparator e2 = new EmployeeComparator("APURVA" , "CS1" , 2000);
        EmployeeComparator e3 = new EmployeeComparator("ABHILASH" , "CS2" , 2500);

        List<EmployeeComparator> empList = new ArrayList<EmployeeComparator>();
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);

        Collections.sort(empList ,new EmployeeSalaryComparator());
        System.out.println(empList);
    }
}


class EmployeeComparator {
    private String name;

    private String departMent;

    private int salary;

    public EmployeeComparator(String name, String departMent, int salary) {
        this.name = name;
        this.departMent = departMent;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartMent() {
        return departMent;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "EmployeeComparator{" +
                "name='" + name + '\'' +
                ", departMent='" + departMent + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class EmployeeNameComparator implements Comparator<EmployeeComparator> {

    @Override
    public int compare(EmployeeComparator o1, EmployeeComparator o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class EmployeeSalaryComparator implements Comparator<EmployeeComparator> {

    @Override
    public int compare(EmployeeComparator o1, EmployeeComparator o2) {
        return o1.getSalary() - o2.getSalary();
    }
}
