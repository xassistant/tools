package com.queue;

import org.junit.Test;

import java.util.*;

/**
 * @Date 2019/2/20 9:30
 */
public class PriorityQueueTest {
    /**
     * PriorityQueue 是实现不同步的
     * PriorityBlockingQueue 这个是实现同步，使用lock
     */
    @Test
    public void test1() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(6);
        priorityQueue.offer(3);
        Iterator iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

    }
}
