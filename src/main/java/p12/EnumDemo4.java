package p12;

enum Apple2 {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland;
}

public class EnumDemo4 {
    public static void main(String[] args) {
        Apple2 ap, ap2, ap3;
        System.out.println("All constants of apples' sort and its " +
                "order values:");
        for(Apple2 a : Apple2.values()){
            System.out.println(a + " " + a.ordinal());
        }

        ap = Apple2.RedDel;
        ap2 = Apple2.GoldenDel;
        ap3 = Apple2.RedDel;

        System.out.println();

        if(ap.compareTo(ap2) < 0){
            System.out.println(ap + " предшествует " + ap2);
        }
        if(ap.compareTo(ap2) > 0){
            System.out.println(ap2 + "предшествует " + ap);
        }
        if(ap.compareTo(ap3) == 0){
            System.out.println(ap + " равно " + ap3);
        }
        System.out.println();

        if(ap.equals(ap2)){
            System.out.println("Error!");
        }
        if(ap.equals(ap3)){
            System.out.println(ap + " равно " + ap3);
        }
        if(ap == ap3){
            System.out.println(ap + " == " + ap3);
        }

        System.out.println("All values and ther ");
    }
}
