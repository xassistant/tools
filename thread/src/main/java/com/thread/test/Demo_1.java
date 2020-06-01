package com.thread.test;

public class Demo_1 {
    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        t1.start();
        t1.join();
        t1.wait();
    }
    public volatile static int j=0;
    public static class AddThread extends Thread{

        @Override
        public void run(){

        }
    }
}