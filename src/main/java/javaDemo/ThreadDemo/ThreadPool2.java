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

//        executorService.scheduleWithFixedDelay(() -> {
//            System.out.println(Thread.currentThread().getName() + (i.getAndIncrement()) + "--start");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + (i.get()) + "--end");
//        }, 0, 1, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + (i.getAndIncrement()) + "--start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + (i.get()) + "--end");
        }, 0, 1, TimeUnit.SECONDS);

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
    public void test3() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactoryBuilder().setNameFormat("conf-file-poller-%d")
                        .build());
        MonitorRunnable monitorRunnable = new MonitorRunnable();
        executorService.scheduleWithFixedDelay(monitorRunnable, 0, 3, TimeUnit.SECONDS);
    }
}

class MonitorRunnable extends StateRunnable implements Runnable {

    @Override
    public void run() {
        if (isStart()) {
            this.start();
            setStart(false);
            System.out.println("start monitor runnable");
        }
    }

    public void start() {
        ScheduledThreadPoolExecutor monitorService = new ScheduledThreadPoolExecutor(10,
                new ThreadFactoryBuilder().setNameFormat(
                        "lifecycleSupervisor-" + Thread.currentThread().getId() + "-%d")
                        .build());
        ScheduledFuture<?> future = monitorService.scheduleWithFixedDelay(
                () -> System.out.println("task"), 0, 3, TimeUnit.SECONDS);
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