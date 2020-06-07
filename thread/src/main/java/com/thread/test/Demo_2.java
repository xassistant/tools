package com.thread.test;

public class Demo_2 {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        if (++i == 0) {
            System.out.println(i);
        }
        int j = 0;
        System.out.println("end:" + i);
        if (j++ == 0) {
            System.out.println(j);
        }
        System.out.println("end:" + j);
    }
    public volatile static int j = 0;

    public static class AddThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1111111);
            }
        }
    }
}