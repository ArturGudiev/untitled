package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Batch {
    String firstPart = "";
    String secondPart = "";
    String filename = "";

    Batch(String filename) {
        this.filename = filename;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstPart = true;
            while ((line = br.readLine()) != null) {
                if (isFirstPart && line.startsWith("goto end")) {
                    isFirstPart = false;
                    continue;
                }
                if (isFirstPart) {
                    firstPart = firstPart + line + "\n";
                }
                if (!isFirstPart) {
                    secondPart = secondPart + line + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Batch batch = new Batch("C:\\Programming\\Batch\\azure.bat");
        Batch batch = new Batch(args[0]);
        batch.modify(args[1], args[2], args[3]);

    }

    private void modify(String tag, String loop, String command) {
        System.out.println(tag + " " +  loop + " " + command);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(firstPart);
            bw.write(String.format("if \"%%1\"==\"%s\" goto %s\ngoto end\n\n" +
                                    ":%s\n" +
                                    "%s\n" +
                                    "goto end\n"
                    ,tag, loop, loop, command));
            bw.write(secondPart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
