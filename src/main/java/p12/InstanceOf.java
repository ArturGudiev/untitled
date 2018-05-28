package p12;

class A{
    int i, j;
}

class B{
    int i, j;
}

class C extends A{
    int k;
}

class D extends A{
    int k;
}

public class InstanceOf {
    public static void main(String[] args) {
        B b = new B();
        A a = new A();
        C c = new C();
        D d = new D();

        if (a instanceof A) {
            System.out.println("AA is A' instance");
        }
        if (b instanceof B) {
            System.out.println("B is instance of B");
        }
        if (c instanceof A) {
            System.out.println("c is instance of C");
        }
        if (c instanceof A) {
            System.out.println("c can be casted to A");
        }
        if (a instanceof C) {
            System.out.println("a can be casted to C");
        }
        System.out.println();
        A ob;

        ob = d;
        System.out.println("new ob references to d");
        if (ob instanceof D) {
            System.out.println("ob is instance of D");
        }else{
            System.out.println("ob can't be casted to D");
        }
        if (ob instanceof A) {
            System.out.println("ob can be casted to A");
        }
        System.out.println();
        if (a instanceof Object) {
            System.out.println("a can be casted to Object");
        }
        if (b instanceof Object) {
            System.out.println("b can be casted to Object");
        }
        if (c instanceof Object) {
            System.out.println("c can be casted to Object");
        }
        if (d instanceof Object) {
            System.out.println("d can be casted to Object");
        }
    }
}
