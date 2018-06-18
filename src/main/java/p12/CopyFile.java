package p12;

import utilities.Clipboard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) throws IOException{
        int i;
        String result = "";
        if (args.length != 2) {
            System.out.println("Using: CopyFIle from where");
            return;
        }
        try (FileInputStream fin = new FileInputStream(args[0]);
             FileOutputStream fout = new FileOutputStream(args[1])) {
            do {
                i = fin.read();
                if (i != -1) {
                    result += i + "\n";
                    fout.write(i);
                }
            } while (i != -1);
            Clipboard.clip(result);
        } catch (IOException e) {
            System.out.println("Error input-output: " + e);
        }

    }
}
