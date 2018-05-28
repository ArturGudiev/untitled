package com.company;

public class QQ {
    int n;
    boolean valueSet = false;

    synchronized int get(){
        while (!valueSet){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("InterruptedException is catched");
            }
        }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;

    }

    synchronized void put(int n){
        while (valueSet){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
            this.n = n;
            valueSet = true;
            System.out.println("Sent: " + n);
            notify();
        }
    }
}

class Producer2 implements Runnable{
    QQ q;

    Producer2(QQ q){
        this.q = q;
        new Thread(this, "Producer").start();
    }
    public void run(){
        int i = 0;
        while(true){
            q.put(i++);
        }
    }
}

class Consumer2 implements Runnable{
    QQ q;

    Consumer2(QQ q){
        this.q = q;
        new Thread(this, "Consumer2").start();
    }

    public void run(){
        while (true){
            q.get();
        }
    }
}

class PCFixed{
    public static void main(String[] args) {
        QQ q = new QQ();
        new Producer2(q);
        new Consumer2(q);

        System.out.println("For interruption Ctrl-C");
    }
}
