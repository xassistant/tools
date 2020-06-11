package com.thread.test;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

public class Demo_1 {
    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        t1.start();
        t1.join();
        t1.wait();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        ((DirectBuffer) byteBuffer).cleaner().clean();

    }
    public volatile static int j=0;
    public static class AddThread extends Thread{

        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep((long) (Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1111111);
            }
        }
    }
}