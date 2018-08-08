package utilities;

import org.apache.commons.io.FileUtils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PasteFile {
    public static void main(String[] args) {
        String path = args[0];
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (cb.isDataFlavorAvailable(DataFlavor.javaFileListFlavor)) {
            try {
                List files = (List) cb.getData(DataFlavor.javaFileListFlavor);
                for (Object o : files) {
                    if (o instanceof File) {
                        File f = (File) o;
                        File newFile = new File(path + "\\" + f.getName());
                        FileUtils.copyFile(f, newFile);
                        System.out.println("\n\t Paste file " + newFile.getName());
                    }
                }

            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
