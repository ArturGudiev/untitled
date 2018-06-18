package temp;
import java.util.*;
import java.text.*;

public class A {

    public static void main(String args[]) {
        Contributor c1 = new Contributor("a", 1);
        Contributor c2 = new Contributor("b", 1);
        Contributor c3 = new Contributor("c", 1);
        List<Contributor> l = new ArrayList<>();
        l.add(c1);
        l.add(c2);
        l.add(c3);
        String[] strings = l.stream().map(el -> el.login).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
    }
}

class Contributor {
    public Contributor() {
    }

    public Contributor(String login, int additions) {
        this.login = login;
        this.additions = additions;
    }

    String login;
    int additions;

    @Override
    public String toString() {

        return "{" +
                "\"login\":\"" + login + '\"' +
                ", \"additions\":" + additions +
                '}';
    }
}
