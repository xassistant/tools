package javaDemo.ThreadDemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date 2019/1/23 11:13
 */
public class ThreadPoolNameTest {
    @Test
    public void shutdownTest() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, new ThreadFactoryBuilder().setNameFormat("conf-file-poller" + Thread.currentThread().getId() + "-%d").build());

        List<Future> list = new ArrayList<>();
        int i = 0;
        while (i < 20) {
            Future<?> submit = executor.submit((Callable<Object>) () -> {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName();
            });
            list.add(submit);
            i++;
            Thread.sleep(100);
        }
        Thread.sleep(5000);
        new Thread(() -> list.forEach(e -> {
            try {
                System.out.println(executor.getActiveCount()+":"+e.get());
                e.cancel(true);
                executor.purge();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        })).start();

        Thread.sleep(10000000);
    }
}
