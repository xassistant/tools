package com.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo_3 {
     public static void main(String[] args) throws InterruptedException {

    }
}
class Producter{
    Lock lock = new ReentrantLock();
    Condition emty = lock.newCondition();
    Condition full = lock.newCondition();

    List<Object> list = new ArrayList();
    int count = 10;
    int tail = 0;
    public void put(Object val) throws InterruptedException {
        lock.lock();
        try {
            while (count == list.size()){
                full.await();
            }
        }finally {
            lock.unlock();
        }
    }
}