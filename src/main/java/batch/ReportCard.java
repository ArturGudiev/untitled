package batch;

import java.util.Arrays;

public class ReportCard {
    public static void main(String[] args) {
        String s = "Table\n" +
                "Report Card\n" +
                "Health Status\n" +
                "Line\n" +
                "Column\n" +
                "Area\n" +
                "Pie\n" +
                "Bullet Chart\n" +
                "Stacked Column\n" +
                "Stacked Area\n" +
                "Timeline\n" +
                "Topology\n";

        for (String str : s.split("\n")) {
            String str2 = str.replace(" ", "");
            String output = String.format("\"%s\" :\"%s\",", str2, str);
            System.out.println(output);
        }
    }
}
