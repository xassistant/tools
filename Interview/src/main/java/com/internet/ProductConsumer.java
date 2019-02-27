package com.internet;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Date 2019/2/18 9:02
 */
public class ProductConsumer<T> {
    // queue
    static class MessageQueue<T> {
        private BlockingQueue<T> queue;

        public MessageQueue(int capcity) {
            queue = new ArrayBlockingQueue(capcity);
        }

        public void put(T message) throws InterruptedException {
            queue.put(message);
        }

        public T get() throws InterruptedException {
            return queue.take();
        }
    }

    // procuct
    static class Product extends Thread {
        MessageQueue messageQueue;

        public Product(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    messageQueue.put(Math.random());
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    // consumer
    static class Consumer extends Thread {
        MessageQueue messageQueue;

        public Consumer(MessageQueue messageQueue) {
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object o = messageQueue.get();
                    System.out.println(o);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(100);
        Product product = new Product(messageQueue);
        Consumer consumer = new Consumer(messageQueue);

        product.start();
        consumer.start();
    }
}
