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
        String fileString = MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\sqlpwd.txt");
        fileString = fileString.substring(0, fileString.length() - 1);
        return fileString;
    }


    public static String geDPAPWDString(){
        return MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\dpapwd.txt");
    }

    public static String getDPASysPWD(){
        String fileString = MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\dpasyspwd.txt");

        return fileString.substring(0, fileString.length() - 1);
    }


    public static String getDPALogin(){
        String fileString = MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\dpalogin.txt");
        return fileString.substring(0, fileString.length() - 1);
    }

    public static void main(String[] args) {
        percent(args[0]);
    }

}
