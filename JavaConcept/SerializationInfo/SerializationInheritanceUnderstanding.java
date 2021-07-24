package JavaConcept.SerializationInfo;

import java.io.*;

public class SerializationInheritanceUnderstanding {
    public static void main(String[] args) throws IOException , ClassNotFoundException {
        System.out.println("Serialization Inheritance Demonstration...");

        B obj = new B(1 , "1" , new C(2));
        ObjectOutputStream os =new ObjectOutputStream(new FileOutputStream(new File("a.ser")));
        os.writeObject(obj);

        ObjectInputStream is =new ObjectInputStream(new FileInputStream("a.ser"));
        B obj1 = (B)is.readObject();
        System.out.println(obj1);
    }
}

class A {
    int aid;
    String aname;

    public A() {
        aid = Integer.MAX_VALUE;
        aname = "DEFAULT_VALUE_SET";
    }

    public A(int aid, String aname) {
        this.aid = aid;
        this.aname = aname;
    }

    @Override
    public String toString() {
        return "A{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                '}';
    }
}

class B extends A implements Serializable{
    int bid;
    String bname;

    C obj;

    public B(int aid, String aname , C obj) {
        super(aid , aname);
        this.bid = aid;
        this.bname = aname;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "B{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                " , aid=" + aid +
                " , aname=" + aname +
                " , c=" + obj +
                '}';
    }
}

class C implements Serializable{
    int cid;

    public C(int cid){
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "C{" +
                "cid=" + cid +
                '}';
    }
}