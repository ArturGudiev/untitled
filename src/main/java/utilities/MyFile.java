package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MyFile {

    public static void main(String args[]) {
        if(args[0].equals("clip-content-file")){
            Clipboard.clip(getFileString(args[1]));
        }

    }

    public static String getFileString(String fileName) {
        String ans = "";
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                ans += scanner.nextLine() + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

}

