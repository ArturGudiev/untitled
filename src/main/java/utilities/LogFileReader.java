package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LogFileReader {
    private String filename;


    LogFileReader(String filename) {
        this.filename = filename;
    }

    private String truncate(){
        int counter = 0;
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstPart = true;
            boolean emptyline = false;
            while ((line = br.readLine()) != null) {
                counter = line.trim().startsWith("at ") || line.trim().startsWith("Caused by: ")
                        ? counter + 1
                        : 0;
                if(counter <= 5 && !line.contains("INFO") && !line.contains("WARN")){
                    result = result + line + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFileReader logFileReader = new LogFileReader(args[0]);
        final String result = logFileReader.truncate();

        File f = new File(logFileReader.filename);
        String newpath = logFileReader.filename + "_truncated";
        if(f.getName().split("\\.").length > 1){
            String name = f.getName().split("\\.")[0];
            String extenstion = f.getName().split("\\.")[1];
            newpath = f.getParent() + "\\" + name + "_truncated" + "." + extenstion;
        }
        System.out.println(newpath);
        Actions.printToFile(newpath, result);


    }
}
