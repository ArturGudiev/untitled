package utilities;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GetFolderSize {

    int totalFolder = 0;
    int totalFile = 0;

    public static void main(String args[]) throws InterruptedException, IOException {
        String folder = args[0];

        long oldSize = 0;
        int times = 0;
        while (true) {
            try {
                GetFolderSize size = new GetFolderSize();
                long fileSizeByte = size.getFileSize(new File(folder));
                long sizeInMb = fileSizeByte / (1024 * 1024);
                if (sizeInMb != oldSize) {
                    System.out.print("\rFolder Size: " + sizeInMb + " Mb");
                    oldSize = sizeInMb;
                    times = 0;
                } else {
                    times++;
                    System.out.print("                                                                      ");
                    System.out.print("\rFolder Size: " + sizeInMb + " Mb (" + times + ")");
                }
                TimeUnit.SECONDS.sleep(1);

            } catch (NullPointerException e) {
                System.out.println("\nThe path specified is empty");
//                final Runnable runnable =
//                        (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
//                if (runnable != null) runnable.run();
//                Toolkit.getDefaultToolkit().beep();
                if (args.length > 1 && args[1].equals("-r") ) {
                    TimeUnit.SECONDS.sleep(1);
                }else{
                    System.exit(0);
                }
            }
        }
    }

    public long getFileSize(File folder) {
        totalFolder++;
        long foldersize = 0;
        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                foldersize += getFileSize(filelist[i]);
            } else {
                totalFile++;
                foldersize += filelist[i].length();
            }
        }
        return foldersize;
    }

    public int getTotalFolder() {
        return totalFolder;
    }

    public int getTotalFile() {
        return totalFile;
    }
}