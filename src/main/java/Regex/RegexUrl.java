package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUrl {
    public static void main(String[] args) {
        String url = "http://regex.info/blog:3333";
        String regex = "(?x) ^(https?)://([^/:]+)(?:(\\d+))?";
        Matcher m = Pattern.compile(regex).matcher(url);

        if (m.find()) {
            System.out.println(
                    "Overall [" + m.group() + "]" +
                            " (from " + m.start() + " to " + m.end() + ")\n" +
                            "Protocol [" + m.group(1) + "]" +
                            " (from " + m.start(1) + " to " + m.end(1) + ")\n" +
                            "Hostname [" + m.group(2) + "]" +
                            " (from " + m.start(2) + " to " + m.end(2) + ")\n");

        }
        if(m.group(3) == null){
            System.out.println("No port; default of '80' is assumed");
        }else{
            System.out.println("Port is [" + m.group(3) + "] " +
            "(from " + m.start(3) + " to " + m.end(3) + ")\n");
        }
    }
}
