package com.thread.test;

public class Demo_4 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        System.out.println(this.getClass().getSimpleName());
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aaaaaa");
        thread1.start();
        thread1.join();

    }
}