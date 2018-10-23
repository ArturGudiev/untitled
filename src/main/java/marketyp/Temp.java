package marketyp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static marketyp.FloydWarshall.getPath;
import static marketyp.FloydWarshall.getStock;

public class Temp {

    static class A {
            String b;

        public A(String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "A{" +
                    "b='" + b + '\'' +
                    '}';
        }
    }
    
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(5);
//        list.add(6);
//        list.add(7);
//
//        int index = 3;
//
//        int u = list.get(index);
//        int v = list.get(index+1);
//
//        List<Integer> list2 = new ArrayList<Integer>();
//        list2.add(u);
//        list2.add(1);
//        list2.add(1);
//        list2.add(v);
//        list.remove(index);
//        list.remove(index);
//        list.addAll(index, list2);
//
//        System.out.println(list);
        List<A> as = Arrays.asList(new A("c"), new A("d"));
        List<String> collect = as.stream().map(e -> e.b).collect(Collectors.toList());

        System.out.println(collect);
        System.out.println("as = " + as);
        List<String> l = Arrays.asList(new String[]{"a", "b"});
        System.out.println(l.contains("c"));
        System.out.println(l.contains("a"));
    }



    static ArrayList<Integer> indexOfAll(Object obj, ArrayList list){
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++)
            if(obj.equals(list.get(i)))
                indexList.add(i);
        return indexList;
    }
}
