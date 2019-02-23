package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private int a = 0;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

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
        String a = "asd";
        switch (a){
            case "asd":
                System.out.println("Here");
                break;
            default:
                System.out.println("Nowhere");
                break;
        }
    }
}
