package temp;

import utilities.Clipboard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.contains("e"));
        System.out.println(list.contains("a"));
        String s = list.stream().
                map(Object::toString).
                collect(Collectors.joining(" ")).toString();
        System.out.println(s);
        System.out.println(list);
    }
}
