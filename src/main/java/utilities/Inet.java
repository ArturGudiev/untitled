package utilities;

import java.io.IOException;

public class Inet {

    public static void navigateToUrl(String url) {
        try {
            url = url.replaceAll("&", "^&");
//            Runtime.getRuntime().exec("cmd /c \"ch " + url + "\"");
            Runtime.getRuntime().exec("cmd /c \"start " + url + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        navigateToUrl("google.com");
    }
}
