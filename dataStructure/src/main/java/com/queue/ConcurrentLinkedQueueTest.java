package com.queue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date 2019/2/20 10:38
 */
public class ConcurrentLinkedQueueTest {
    @Test
    public void test1() {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
    }

    ReentrantLock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition full = lock.newCondition();
    LinkedList<String> queue = new LinkedList();

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueueTest test = new ConcurrentLinkedQueueTest();
        test.product();
        test.consumer();
    }

    private void consumer() throws InterruptedException {
        lock.lock();
        try {
            if (queue.size() == 0) {
                full.signal();
                empty.await();
            }
            queue.poll();
            System.out.println();
        } finally {
            lock.unlock();
        }

    }

    private void product() {
    }

}
