package javaDemo.ThreadDemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xlj
 * @Date 2019/1/7 16:29
 */
public class ThreadPool2 {
    @Test
    public void test1() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactoryBuilder().setNameFormat("conf-file-poller-%d")
                        .build());
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

        // 应为程序占用的时间，不推迟了
//        executorService.scheduleAtFixedRate(() -> {
//            System.out.println(Thread.currentThread().getName() + (i.get()) + "--start");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + (i.getAndIncrement()) + "--end");
//        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(100000);
    }

    @Test
    public void test2() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "--1--" + Thread.currentThread().getState());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                thread1.start();
                boolean flag = true;
                int i = 0;
                while (flag) {
                    i++;
                    if (i == 4) {
                        flag = false;
                    }
                    System.out.println(Thread.currentThread().getName() + "==2==" + Thread.currentThread().getState());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        Thread.sleep(1000000);
    }

    @Test
    public void test3() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactoryBuilder().setNameFormat("conf-file-poller-%d")
                        .build());
        MonitorRunnable monitorRunnable = new MonitorRunnable("");
        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(monitorRunnable, 0, 3, TimeUnit.SECONDS);
        Thread.sleep(2000);
        scheduledFuture.cancel(true);
        Thread.sleep(1000000);
    }

    @Test
    public void test4() throws InterruptedException {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(2,
                new ThreadFactoryBuilder()
                        .setNameFormat("conf-file-poller-%d")
                        .build());
        pool.setMaximumPoolSize(3);
        MonitorRunnable m1 = new MonitorRunnable("monitor----1");
        MonitorRunnable m2 = new MonitorRunnable("monitor----2");
        MonitorRunnable m3 = new MonitorRunnable("monitor----3");
        MonitorRunnable m4 = new MonitorRunnable("monitor----4");
        MonitorRunnable m5 = new MonitorRunnable("monitor----5");


        ScheduledFuture<?> s1 = pool.scheduleWithFixedDelay(m1, 0, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> s2 = pool.scheduleWithFixedDelay(m2, 0, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> s3 = pool.scheduleWithFixedDelay(m3, 0, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> s4 = pool.scheduleWithFixedDelay(m4, 0, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> s5 = pool.scheduleWithFixedDelay(m5, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(4000);
        s2.cancel(true);
        pool.purge();// 取消后要移除

        Thread.sleep(1000000);
    }
}

class MonitorRunnable extends StateRunnable implements Runnable {
    String name;

    public MonitorRunnable(String s) {
        this.name = s;
    }

    @Override
    public void run() {
//        while (!isStart()) {
        try {
            System.out.println(name + "::" + Thread.currentThread().getName());
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            setStart(true);
        }
//        }
    }

}

class StateRunnable {
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}