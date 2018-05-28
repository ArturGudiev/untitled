package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PercentForBatchScript {
    public static void main(String[] args) {
        String s2 = "https://mail.google.com/mail/u/0/#label/%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82%2F%D0%9D%D0%B5%D0%BC%D0%B5%D1%86%D0%BA%D0%B8%D0%B9";
//        String s = "https://www.youtube.com/results?search_query=%D0%B2%D0%B5%D1%81%D1%82%D0%B8+%D0%B8%D1%80%D1%8B%D1%81%D1%82%D0%BE%D0%BD";
        String s3 = "æ";
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < s.length(); i++){
//            sb.append( s.charAt(i) == '%' ? "%%" : s.charAt(i));
//        }
        String s = "cmd.exe /c echo | set /p=æ| clip";
        executeCommand(s);
    }
    private static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
