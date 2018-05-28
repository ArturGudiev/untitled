package com.company;

enum Apple{
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}

public class EnumDemo2 {
    public static void main(String[] args) {
        Apple ap;
        System.out.println("Constants of iteraple type Apple:");

        Apple allApples[] = Apple.values();
        for(Apple a : allApples){
            System.out.println(a);
        }
        System.out.println();
        ap = Apple.valueOf("Winesap");
        System.out.println("var ap contains " + ap);
    }
}
