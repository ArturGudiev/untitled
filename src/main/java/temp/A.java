package temp;
import java.lang.annotation.Native;
import java.util.*;
import java.text.*;

import static java.awt.SystemColor.windowText;

public class A {

    public static void main(String args[]) {


    }

    public void f(){
//        String wText = Native.toString(windowText, System.getProperty("file.encoding")).trim();
//        com.sun.jna.platform.win32.WinDef.HWND hwnd_1 = new WinDef.HWND(hWnd);
//        boolean b = com.sun.jna.platform.win32.User32.INSTANCE.IsWindowVisible(hwnd_1);
//        if (!wText.isEmpty() && b) {
//            windowNames.add(wText);
//        }
    }
}

class Contributor {
    public Contributor() {
    }

    public Contributor(String login, int additions) {
        this.login = login;
        this.additions = additions;
    }

    String login;
    int additions;

    @Override
    public String toString() {

        return "{" +
                "\"login\":\"" + login + '\"' +
                ", \"additions\":" + additions +
                '}';
    }
}

