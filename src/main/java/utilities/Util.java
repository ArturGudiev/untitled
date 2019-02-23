package utilities;

import com.sun.deploy.util.StringUtils;
import static utilities.Clipboard.clip;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;

public class Util {
    public static void percent(String s){
        String newStr = s.replaceAll("%", "%%");
        newStr = newStr.replaceAll("&", "^&");
        System.out.println(newStr);
        clip(newStr);
    }

    public static String getSQLPWDString(){
        return MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\sqlpwd.txt");
    }


    public static String geDPAPWDString(){
        return MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\sqlpwd.txt");
    }

    public static void main(String[] args) {
        percent(args[0]);
    }

}
