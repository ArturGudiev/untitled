package p12;

enum Apple{
    Jonathan(10), GoldenDel(9), RedDek(12), Winesap(15), Cortland(8);
    private int price;

    Apple(int p){price = p;}
    int getPrice(){return price;}
}

public class EnumDemo3 {
    public static void main(String[] args) {
        Apple ap;
        System.out.println("Яблоко сорта Winesap стоит " + Apple.Winesap.getPrice() + "центов. \n");
        System.out.println("Цены на все сорта яблок: ");
        for(Apple a : Apple.values()){
            System.out.println(a + " стоит " + a.getPrice() + " центов");
        }
    }
}
