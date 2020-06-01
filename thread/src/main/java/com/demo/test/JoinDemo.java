package com.demo.test;

public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Join2Demo join2Demo = new Join2Demo();
        Thread thread = new Thread(() -> {
            try {
                join2Demo.test("2222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                join2Demo.test("1111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
    }
}

class Join2Demo {

    public synchronized void test(String name) throws InterruptedException {
        System.out.println(name + " 等待10秒");
        Thread.sleep(10000);
    }

}