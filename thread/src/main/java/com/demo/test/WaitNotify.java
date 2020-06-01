package com.demo.test;

public class WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    waitNotifyDemo.test1();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    waitNotifyDemo.test2();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
    }

}

class WaitNotifyDemo {
    static boolean test1;

    public synchronized void test1() throws InterruptedException {
        if (test1) {
            System.out.println("1111 运行");
            Thread.yield();
            test1 = false;
            notify();
        } else {
            System.out.println("1111 等待");
            wait();
        }
    }

    public synchronized void test2() throws InterruptedException {
        if (test1) {
            System.out.println("2222 等待");
            wait();
        } else {
            System.out.println("2222 运行");
            Thread.sleep(500);
            test1 = true;
            notify();
        }
    }

    public void test3(){
        synchronized (this){
            System.out.println(1);
        }
    }
}
