package com.concurrent;

import java.util.concurrent.ThreadPoolExecutor;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        //sleepThread睡眠1000ms
        final Thread sleepThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(11000);
                    System.out.println("sleepThread");
                } catch (InterruptedException e) {

                }
            }
        };
        //busyThread一直执行死循环
        Thread busyThread = new Thread() {
            @Override
            public void run() {
                boolean flag = true;
                while (flag) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("busyThread");
                    } catch (InterruptedException e) {
                        flag = false;
                    }
                }
            }
        };
        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
        System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());
        while (sleepThread.isInterrupted()) {
            System.out.println("sleepThread.isInterrupted()");
        }

        while (busyThread.isInterrupted()) {
            System.out.println("busyThread.isInterrupted()");
        }
        System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());
        System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());

    }
}