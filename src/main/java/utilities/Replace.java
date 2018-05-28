package utilities;

public class Replace {
    public static void main(String[] args) {
        if(args.length < 3){
            System.out.println("Incorrect arguments");
            System.exit(1);
        }
        String modifiedString = args[0].replaceAll(args[1], args[2]);
        Clipboard.clip(modifiedString);
        System.out.println(modifiedString);
    }
}
