package temp;

public class TestNewBuilder {
    private int a;

    public TestNewBuilder setA(int a) {
        this.a = a;
        return this;
    }

    public TestNew createTestNew() {
        return new TestNew(a);
    }
}