package JavaConcept.Cloning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class ShallowCloneEmployee implements Cloneable{
    int empId;

    String empName;

    Map<String,Integer> props;

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

    public Map<String, Integer> getProps() {
        return props;
    }

    public void setProps(Map<String, Integer> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", props=" + props +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}



class DeepCloneEmployee implements Cloneable{
    int empId;

    String empName;

    Map<String,Integer> props;

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

    public Map<String, Integer> getProps() {
        return props;
    }

    public void setProps(Map<String, Integer> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", props=" + props +
                '}';
    }

    @Override
    public DeepCloneEmployee clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        DeepCloneEmployee deepEmployee = (DeepCloneEmployee) obj;
        deepEmployee.setEmpId(this.empId);
        deepEmployee.setEmpName(this.empName);
        Map<String,Integer> clonedProps = new HashMap<>();
        Iterator<String> it = this.props.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            clonedProps.put(key , this.props.get(key));
        }
        setProps(clonedProps);
        return deepEmployee;
    }
}




public class ClonigDemonstration extends Object{
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("Cloning Demonstration...");
        Map<String,Integer> props = new HashMap<>();
        props.put("A" , 1);
        props.put("B" , 2);

        ShallowCloneEmployee e1 = new ShallowCloneEmployee();
        e1.setEmpId(1);
        e1.setEmpName("Abhilash");
        e1.setProps(props);

        ShallowCloneEmployee cloned = (ShallowCloneEmployee) e1.clone();
        e1.getProps().put("C" , 3);
        e1.setEmpName("Apurva");

        System.out.println("Default clone method of object class uses shallow copy that is created using reflection , so all non - primitive objects are shared between original object and cloned object,so any changes made to them are reflected in both collection.");
        System.out.println("Original Employee - " + e1);
        System.out.println("Cloned Employee - " + cloned);

        System.out.println("Are both map references same - " + (e1.getProps() == cloned.getProps()));

        System.out.println("So if we want to ensure , we have new instance returned that is different from original instance , then we will need to perform deep copy , and also for deep coppy , we will have to do the non-primitive clone by ourself.");

        DeepCloneEmployee e3 = new DeepCloneEmployee();
        e3.setEmpId(1);
        e3.setEmpName("Abhilash");
        e3.setProps(props);

        DeepCloneEmployee deepcloned = (DeepCloneEmployee) e3.clone();
        e3.getProps().put("D" , 4);
        e3.setEmpName("Sharvil");
        System.out.println("Original Employee - " + e3);
        System.out.println("DeepCloned Employee - " + deepcloned);
        System.out.println("Are both map references same - " + (e3.getProps() == deepcloned.getProps()));
    }
}
