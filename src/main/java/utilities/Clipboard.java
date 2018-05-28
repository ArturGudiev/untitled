package utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class Clipboard {
    public static void clip(String str) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(str);
        clipboard.setContents(strSel, null);
    }
}
