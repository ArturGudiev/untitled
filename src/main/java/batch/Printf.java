package batch;

public class Printf {
    static String str = "" +
            "Numpad2 & r    -   reload Hotkey script\n" +
            "Numpad2 & s    -   switch User\n" +
            "Numpad2 & c    -   close all windows";

    static String str2 =
                    "Numpad1 & j    -   Board\n" +
                    "Numpad1 & w    -   Work\n" +
                    "Numpad1 & t    -   Task\n" +
                    "Numpad1 & c    -   Crucible\n" +
                    "Numpad1 & l    -   Localhost\n" +
                    "Numpad1 & m    -   Maps\n";

    static String str3 = "" +
            "Numpad0 & m    -   Moba\n" +
            "Numpad0 & c    -   Chrome\n" +
            "Numpad0 & i    -   Idea\n" +
            "Numpad0 & a    -   Actions\n" +
            "Numpad0 & r    -   Reload\n" +
            "Numpad0 & h    -   Hotkey tutorial\n" +
            "Numpad0 & o    -   Outlook\n" +
            "Numpad0 & s    -   Services\n";

    public static void wrapToPrintf(String rowString) {
        for(String s : rowString.split("\n")){
            System.out.println("    printf \"   " + s + "\\n\"");
        }
    }

    public static void main(String[] args) {
        wrapToPrintf(str3);
    }
}
