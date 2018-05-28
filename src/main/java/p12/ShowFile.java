package p12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ShowFile {
    public static void main2(String[] args) {
        int i;
        BufferedReader bin;

        if(args.length != 1){
            System.out.println("Using: ShowFile filename");
            return;
        }
        try {
            bin = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), "UNICODE"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        try {
            do {
                i = bin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            } while (i != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
