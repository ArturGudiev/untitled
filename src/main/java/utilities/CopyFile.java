package utilities;

public class CopyFile {
    public static void main(String[] args) {
        Clipboard.clip(MyFile.getFileString(args[0]));
    }
}
