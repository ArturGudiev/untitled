package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

interface Updater {
    void update(String value);
}

public class Lambda {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        Consumer<Integer> f = (a) -> {
            list.add(a);
        };

        f.accept(5);
        System.out.println(list);

        f.accept(6);
        System.out.println(list);
    }

}
