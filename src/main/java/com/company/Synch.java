package com.company;


class Callme{
    void call(String msg){
        System.out.print("[" + msg);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Is interrupted");
        }
        System.out.print("]");
    }
}

class Caller implements Runnable{
    String msg;
    Callme target;
    Thread t;

    public Caller(Callme targ, String s){
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }
    public void run(){
        synchronized (target){
            target.call(msg);
        }
    }
}

public class Synch {
    public static void main(String[] args) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "You are welcome");
        Caller ob2 = new Caller(target, "in synchronized");
        Caller ob3 = new Caller(target, "world");

        try{
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch (InterruptedException e){
            System.out.println("Is interrupted");
        }
    }
}

class Synch1{
    public static void main(String[] args) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "You are welcome");
        Caller ob2 = new Caller(target, "in syncronized");
        Caller ob3 = new Caller(target, "world!");

        try{
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch (InterruptedException e){
            System.out.println("Is been interrupted");
        }
    }
}
