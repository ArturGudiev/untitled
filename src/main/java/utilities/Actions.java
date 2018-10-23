package utilities;

import database.Postgres;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Actions {
    public static void createDirectory(String name){
        Path path = Paths.get(name);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }

    public static void createFile(String name){
        Path path = Paths.get(name);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }

    public static void appendToFile(String filePath, String text){
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static String[] getListOfDirectories(String pathToDir){
        File file = new File(pathToDir);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }

    public static void main(String[] args) {
        getListOfDirectories("C:\\Artur\\Work\\tasks\\");
    }
}
