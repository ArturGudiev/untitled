package p12;

import java.lang.annotation.Native;

public class NativeDemo {
    int i;

    public static void main(String[] args) {
        NativeDemo ob = new NativeDemo();

        ob.i = 10;
        System.out.println("Before ob.i = " + ob.i);
        ob.test();
        System.out.println("After ob.i = " + ob.i);

    }

    public native void test();

    static {
        System.loadLibrary("NativeDemo");
    }
}
