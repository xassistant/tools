package com.thread.test;

public class Demo_1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread();
        t1.start();
        t1.join();
        t1.wait();
    }
}
