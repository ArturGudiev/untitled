package utilities;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CutFile {

    public static void main(String[] args) {
        File file = new File(args[0]);

        System.out.println("\n\tCut " + (file.isFile() ? "file" : "directory") + " " +  file.getName());
        List listOfFiles = new ArrayList();
        listOfFiles.add(file);

        FileTransferable ft = new FileTransferable(listOfFiles);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ft, new ClipboardOwner() {
            @Override
            public void lostOwnership(Clipboard clipboard, Transferable contents) {
                System.out.println("Lost ownership");
            }
        });

        Actions.deleteFile(args[0]);

    }

    public static class FileTransferable implements Transferable {

        private List listOfFiles;

        public FileTransferable(List listOfFiles) {
            this.listOfFiles = listOfFiles;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.javaFileListFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.javaFileListFlavor.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return listOfFiles;
        }
    }

}
