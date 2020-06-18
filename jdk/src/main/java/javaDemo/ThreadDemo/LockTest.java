package javaDemo.ThreadDemo;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author xlj
 * @Date 2019/1/7 17:29
 */
public class LockTest {
    @Test
    public void test3() throws Exception {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

//                lock.lock();
	        	try {
					lock.lockInterruptibly();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }
    @Test
    public void test1() throws InterruptedException {
        LockTest lockTest = new LockTest();

        new Thread(() -> {
            try {
                lockTest.test11();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(500);
        new Thread(() -> {
            try {
                lockTest.test12();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000000);
    }
    Lock lock = new ReentrantLock();
    public  void test11() throws InterruptedException {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000000);
//            test12();
        } finally {
            lock.unlock();
        }
    }

    public  void test12() throws InterruptedException {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
        } finally {
            lock.unlock();
        }
    }
}
