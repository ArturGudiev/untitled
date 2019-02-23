package temp;

import utilities.MyFile;

public class PR {
    public static void main(String[] args) {
        final String fileString = MyFile.getFileString("C:\\Programming\\Java\\untitled\\resources\\sqlpwd.txt");
        System.out.println(fileString);
    }
}
