package utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.concurrent.TimeUnit;

public class FolderSize {
    public static void main(String[] args) {
        try {
            while(true) {
                long size = Files.size(Paths.get(args[0]));
                System.out.println(size/1024);
                FileUtils.sizeOfDirectory(new File(args[0]));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
