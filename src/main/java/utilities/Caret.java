package utilities;

import java.util.concurrent.TimeUnit;

public class Caret {
    public static void main(String[] args) throws InterruptedException {
        int a = 10;
        for (int i = 0; i < 10000; i++) {
            a = (a*10) % 999;
            System.out.print("\r\b'Hello " + a);
            TimeUnit.SECONDS.sleep(1);

        }
    }
}
