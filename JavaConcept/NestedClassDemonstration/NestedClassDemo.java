package JavaConcept.NestedClassDemonstration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NestedClassDemo {
    public static void main(String[] args) {

        NestedClass nc = new NestedClass();
        nc.setClassId(10);
        NestedClass.InnerNestedClass inc = nc.new InnerNestedClass();
        inc.setInnerClassId(20);
        System.out.println(nc.getClassId()+":"+inc.getInnerClassId() + ":"+inc.printOuterClassVariable());

        Field[] fields =  NestedClass.InnerNestedClass.class.getDeclaredFields();
        Method[] methods =  NestedClass.InnerNestedClass.class.getDeclaredMethods();
        for(Field f : fields){
            System.out.println("FIELD = " + f.getName() + " = " + f.getType());
        }

        for(Method m : methods){
            System.out.println("METHOD = " + m.getName());
        }

        System.out.println("Static Nested Class");
        StaticNestedClass.StaticInnerNestedClass isnc = new StaticNestedClass.StaticInnerNestedClass();
        System.out.println(isnc.printOuterClassVariable());

    }
}


class StaticNestedClass {
    private static int classId = 89;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    static class StaticInnerNestedClass {

        private int innerClassId;

        public int getInnerClassId() {
            return innerClassId;
        }

        public void setInnerClassId(int innerClassId) {
            this.innerClassId = innerClassId;
        }

        public String printOuterClassVariable() {
            return "Outer Class Data :- " + classId;
        }
    }
}










class NestedClass {
    private int classId;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    class InnerNestedClass {

        private int innerClassId;

        public int getInnerClassId() {
            return innerClassId;
        }

        public void setInnerClassId(int innerClassId) {
            this.innerClassId = innerClassId;
        }

        public String printOuterClassVariable() {
            return "Outer Class Data :- " + classId;
        }
    }
}



