package JavaConcept.SerializationInfo;

import java.io.*;

class EmployeeSerializationInfo implements Serializable {

    private static final long serialVersionUID = 1234567L;
    private int empId;
    private String empName;
    private String empAddress;
    transient private int deptId = 99;
    static public int salary;

    public EmployeeSerializationInfo(int empId, String empName, String empAddress , int deptId) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}


class EmployeeExternalizationInfo implements Externalizable {

    private static final long serialVersionUID = 1234567L;
    private int empId;
    private String empName;
    private String empAddress;
    transient private int deptId;
    static public int salary;

    public EmployeeExternalizationInfo(int empId, String empName, String empAddress , int deptId) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.deptId = deptId;
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

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public static int getSalary() {
        return salary;
    }

    public static void setSalary(int salary) {
        EmployeeExternalizationInfo.salary = salary;
    }

    public EmployeeExternalizationInfo() {
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", deptId=" + deptId +
                ", salary=" + salary +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(empId);
        out.writeUTF(empName);
        out.writeUTF(empAddress);
        out.writeInt(deptId);
        out.writeInt(salary);
        System.out.println("Written successfully");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.empId = in.readInt();
        this.empName = in.readUTF();
        this.empAddress = in.readUTF();
        this.deptId = in.readInt();
        salary = in.readInt();
    }
}


public class SerializationDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Using Externalizable interface , we can customize the serialization and deserialization process.Internally Externalizable interface extends Serializable interface");
        EmployeeSerializationInfo.salary = 10000;

        EmployeeSerializationInfo emp = new EmployeeSerializationInfo(1,"Abhilash",
                "Pune" , 3);

        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File("emp.ser")));
        oos.writeObject(emp);
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("emp.ser"));
        EmployeeSerializationInfo e = (EmployeeSerializationInfo) ois.readObject();
        System.out.println("Employee Info Stored is - " + e);


        EmployeeExternalizationInfo emp1 = new EmployeeExternalizationInfo(10,"Sharvil",
                "Pune" , 3);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("emp1.ser")));
        emp1.writeExternal(os);
        os.flush();
        os.close();


        EmployeeExternalizationInfo emp2 = new EmployeeExternalizationInfo();
        ObjectInputStream oois = new ObjectInputStream(new FileInputStream("emp1.ser"));
        emp2.readExternal(oois);
        System.out.println("Employee info - " + emp2);
        oois.close();
    }

    private static class PrivateClassInstance {
        public int id;
        private int eid;
        protected int did;

        public PrivateClassInstance(int id, int eid, int did) {
            this.id = id;
            this.eid = eid;
            this.did = did;
        }

        @Override
        public String toString() {
            return "PrivateClassInstance{" +
                    "id=" + id +
                    ", eid=" + eid +
                    ", did=" + did +
                    '}';
        }
    }
}
