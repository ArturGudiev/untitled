import org.junit.Assert;
import org.junit.Test;

public class A {

    @Test
    public void rightSetterGetter(){
        temp.Test test = new temp.Test();
        test.setA(234);
        Assert.assertEquals(test.getA(), 234);
    }
}
