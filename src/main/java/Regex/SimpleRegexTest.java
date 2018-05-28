package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegexTest {
    public static void main(String[] args) {
        String myText = "[2nd] this is my [1st] test string";
        String myRegex = "\\d+\\w+";
        Pattern p = Pattern.compile(myRegex);
        Matcher m = p.matcher(myText);
        while (m.find()) {
            String matchedText = m.group();
            int matchedFrom = m.start();
            int matchedTo = m.end();
//            System.out.println("matched [" + matchedText + "] " + " from " + matchedFrom + " to ");
            System.out.println(String.format("matched [%s] from %s to %s.", matchedText, matchedFrom, matchedTo));
        }
    }
}
