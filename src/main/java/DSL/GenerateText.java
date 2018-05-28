package DSL;

import utilities.Clipboard;

import java.util.Arrays;
import java.util.stream.Stream;

public class GenerateText {
    public static String generateText(String text) {
        String ans =
                "        <Cell Name=\"Cell2\">\n" +
                "            <TextBlock Name=\"TextBlock1\" VerticalAlignment=\"Center\" HorizontalAlignment=\"Center\" " +
                        "VerticalSizeComputing=\"WrapContent\" HorizontalSizeComputing=\"WrapContent\" Padding=\"5\" " +
                        "FontSize=\"1 * Medium\" Text=\"" + text + "\" />\n" +
                "        </Cell>\n";
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println("args = " + Arrays.toString(args));
        String ans = Stream.of(args)
                .map(e -> generateText(e))
                .reduce((x, y) -> x + y).get();
        Clipboard.clip(ans);
        System.out.println(ans);
    }
}
