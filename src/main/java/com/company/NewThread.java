package com.company;

public class NewThread implements Runnable {
    Thread t;
    String name;

    NewThread(){
        t = new Thread(this, "Демонстрационный поток");
        System.out.println("Дочерний поток создан: " + t);
        t.start();
    }
    NewThread(String name){
        this.name = name;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    public void run() {
        try{
            for (int i = 5; i > 0 ; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("Дочерний поток прерван");
        }
        System.out.println("Дочерник поток завершен");
    }
}

class ThreadDemo {
    public static void main(String[] args) {
        new NewThread();
        try{
            for (int i = 5; i > 0 ; i--) {
                System.out.println("Main thread: " + i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("The main thread is been interrupted");
        }
        System.out.println("The main thread is ended");
    }
}

class MultiTheradDemo{
    public static void main(String[] args) {
        new NewThread1("One");
        new NewThread1("Two");
        new NewThread1("Three");

        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println("The main thread is been interrupted");
        }
        System.out.println("The main thread is interrupted");
    }
}

class DemoJoin{
    public static void main(String[] args) {
        NewThread1 ob1 = new NewThread1("One");
        NewThread1 ob2 = new NewThread1("Two");
        NewThread1 ob3 = new NewThread1("Three");

        System.out.println("Thread one is run: " + ob1.t.isAlive());
        System.out.println("Thread two is run: " + ob2.t.isAlive());
        System.out.println("Thread three is run: " + ob3.t.isAlive());

        try{
            System.out.println("Ожидание завершения потоков.");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch (InterruptedException e){
            System.out.println("The main thread is been interrupted");
        }

        System.out.println("Thread one is run: " + ob1.t.isAlive());
        System.out.println("Thread two is run: " + ob2.t.isAlive());
        System.out.println("Thread three is run: " + ob3.t.isAlive());

    }
}
