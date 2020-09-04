package javaDemo.ThreadDemo;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date 2019/1/23 11:13
 */
public class ThreadPoolShutdownTest {
    @Test
    public void shutdownTest() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(
                new com.google.common.util.concurrent.ThreadFactoryBuilder().setNameFormat("conf-file-poller-%d").build());
        AtomicInteger i = new AtomicInteger();
        // 推迟delay
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + (i.get()) + "--start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + (i.getAndIncrement()) + "--end");
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(1000);
        System.out.println("will shutdown ");
        executorService.shutdown();
        System.out.println("shudown is execute");
        System.out.println("is terminated  " + executorService.isTerminated());
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Thread classIsRunning;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolShutdownTest test = new ThreadPoolShutdownTest();
        Thread thread = new Thread(() -> test.syncClassTest());
        thread.start();
        thread.interrupt();
        Thread.sleep(1000);
        test.syncClassTest1();
        test.classIsRunning.start();
        test.classIsRunning.interrupt();
        System.out.println("============");
        Thread.sleep(1000000);
    }

    public synchronized void syncClassTest() {
        System.out.println("class is running");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void syncClassTest1() {
        classIsRunning = new Thread(() -> {
            synchronized (this) {
                System.out.println("class is running----");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
