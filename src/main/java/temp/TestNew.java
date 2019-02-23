package temp;

public class TestNew implements ITestNew {
    int a;

    public TestNew(int a) {
        this.a = a;
    }

    public TestNew() {

    }

    public int f(TestNewObj testNewObj) {
        System.out.println("AAAAAAAAAAAA");
        
        return 0;
    }

    @Override
    public int g(int a, String b, boolean c) {
        return f(new TestNewObj(b, a, c, "asd", 2, null));
    }

    public static void main(String[] args) {
        TestNew testNew = new TestNewBuilder().createTestNew();
    }
}
