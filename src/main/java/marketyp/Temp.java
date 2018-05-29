package marketyp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Temp {

    public static void main(String[] args) {
        Env env = Env.INSTANCE;
        env.main(null);
        List<Integer> l2 = Arrays.asList(new Integer[]{1, 2, 4,5,4,3});
        List<Integer> l = Arrays.asList(new Integer[]{1, 2, 3});
        l.set(2, 10);
        System.out.println(l);
        System.out.println(l2);
    }


    static ArrayList<Integer> indexOfAll(Object obj, ArrayList list){
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++)
            if(obj.equals(list.get(i)))
                indexList.add(i);
        return indexList;
    }
}
