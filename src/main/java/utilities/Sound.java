package utilities;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

public class Sound {
    public static void main(String[] args) throws InterruptedException {
//        Toolkit.getDefaultToolkit().beep();
        TimeUnit.SECONDS.sleep(1);
//        Toolkit.getDefaultToolkit().beep();
        final Runnable runnable =
                (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        if (runnable != null) runnable.run();
        TimeUnit.SECONDS.sleep(1);
        Toolkit.getDefaultToolkit().beep();

    }
}
