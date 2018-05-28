package com.company;

class A{
    synchronized void foo(B b){
        String name = Thread.currentThread().getName();

        System.out.println(name + "went in method A.foo()");

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("Class A was interrupted");
        }
        System.out.println(name + "is going to call method B.last()");
        b.last();
    }
    synchronized void last(){
        System.out.println("In method A.last()");
    }
}

class B{
    synchronized void bar(A a){
        String name = Thread.currentThread().getName();
        System.out.println(name + "went in B.bar()");

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("Class B was interrupted");
        }
        System.out.println(name + " is going to call A.last()");
        a.last();
    }
    synchronized void last(){
        System.out.println("In method A.last()");
    }
}

public class Deadlock implements Runnable{
    A a = new A();
    B b = new B();

    Deadlock(){
        Thread.currentThread().setName("The main thread");
        Thread t = new Thread(this, "Concurrent thread");
        t.start();

        a.foo(b);

        System.out.println("Back to the main thread");
    }

    public void run(){
        b.bar(a);
        System.out.println("Back to the another thread");
    }

    public static void main(String[] args) {
        new Deadlock();
    }
}
