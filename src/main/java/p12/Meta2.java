package p12;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno2{
    String str();
    int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What {
    String description();
}

@What(description = "????????? ????????? ??????")
@MyAnno2(str = "Meta2", val = 99)
public class Meta2{
    @What(description = "Annotation of test method")
    @MyAnno2(str = "Testing", val = 100)
    public static void myMeth(){
        Meta2 ob = new Meta2();
        try {
            Annotation annos[] = ob.getClass().getAnnotations();
            System.out.println("All annotations of Meta2");
            for (Annotation a : annos)
                System.out.println(a);
            System.out.println();
            Method m = ob.getClass().getMethod("myMeth");
            annos = m.getAnnotations();
            System.out.println("All annotations of method MyMeth()");
            for (Annotation a : annos)
                System.out.println(a);
        } catch (NoSuchMethodException exc) {
            System.out.println("Method is not found");
        }
    }

    public static void main(String[] args) {
        myMeth();
    }
}