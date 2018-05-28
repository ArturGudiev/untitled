package com.company;


class NewThread1 implements Runnable{
    String name;
    Thread t;
    boolean suspendFlag;

    NewThread1(String threadname){
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        suspendFlag = false;
        t.start();
    }

    public void run(){
        try{
            for (int i = 15; i > 0 ; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this){
                    while(suspendFlag){
                        wait();
                    }
                }
            }
        }catch (InterruptedException e){
            System.out.println(name + " is interrupted");
        }
        System.out.println(name + " is finished");
    }

    synchronized void mysuspend(){
        suspendFlag = true;
    }

    synchronized void myresume(){
        suspendFlag = false;
        notify();
    }
}
public class SuspendResume {
    public static void main(String[] args) {
        NewThread1 ob1 = new NewThread1("One");
        NewThread1 ob2 = new NewThread1("Two");

        try{
            Thread.sleep(1000);
            ob1.mysuspend();
            System.out.println("Stop of thread One");
            Thread.sleep(1000);
            ob1.myresume();
            System.out.println("Restruction of thread One");
            ob2.mysuspend();
            System.out.println("Stop of thread Two");
            Thread.sleep(1000);
            ob2.myresume();
            System.out.println("Restruction of thread Two");
        }catch (InterruptedException e){
            System.out.println("The main thread is interrupted");
        }
        try{
            System.out.println("Waiting for finishing of threads");
            ob1.t.join();
            ob2.t.join();
        }catch (InterruptedException e){
            System.out.println("The main thread is interrupted");
        }
        System.out.println("The main thread is finished");
    }
}
