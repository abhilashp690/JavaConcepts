package JavaConcept.ObjectOrientedDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualsHashCodeImplementation {

    public static void main(String[] args) {
        Map<Employee , Integer> empSalary = new HashMap<>();

        Employee e1 = new Employee(1 , "Abhilash1" , "Pune1");
        Employee e2 = new Employee(2 , "Abhilash2" , "Pune2");
        Employee e3 = new Employee(3 , "Abhilash3" , "Pune3");

        empSalary.put(e1 , 100);
        empSalary.put(e2 , 200);
        empSalary.put(e3 , 300);

        System.out.println(" Employee's Salary having name as :-  " + empSalary.get(
                new Employee(2 , "Abhilash2" , "Pune2")));
        System.out.println("Above query will return null as salary , even though data is present" +
                " in the cache , reason being we have not overriden the hashcode method yet , override hashcode" +
                " method and it will work");

    }

}

class Employee {
    private int empId;
    private String empName;
    private String empAddr;

    public Employee(int empId, String empName, String empAddr) {
        this.empId = empId;
        this.empName = empName;
        this.empAddr = empAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId &&
                Objects.equals(empName, employee.empName) &&
                Objects.equals(empAddr, employee.empAddr);
    }

    @Override
    public int hashCode() {
        int finalHash = Objects.hash(empId, empName, empAddr);
        System.out.println("Generated Hash is :- " + finalHash);
        return finalHash;
    }
}
