package temp;

import java.lang.reflect.Array;

public class Test {
    public static void main(String[] args) {
        String s1 = "s1 52";
        String s2 = "s1 8";
        String s3 = "s1 ssssssssssssss";
        System.out.println(s1.compareTo(s2));
        System.out.println(s1.compareTo(s3));
        System.out.println(s2.compareTo(s3));
    }
}
