package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleReplacement {
    public static void main(String[] args) {
        String text = "Before Java 1.5 was Java 1.4.2. After Java 1.5 is Java 1.6";
        String regex = "\\bJava\\s*1\\.5\\b";
        Matcher m = Pattern.compile(regex).matcher(text);
        String result = m.replaceAll("Java 5.0");
        System.out.println(result);
        System.out.println(Pattern.compile("\\bJava\\s*1\\.5\\b").matcher(text).replaceAll("Java 5.0"));
    }
}
