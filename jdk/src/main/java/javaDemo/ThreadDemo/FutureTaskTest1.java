package javaDemo.ThreadDemo;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest1 {
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
            }
        };
        FutureTask futureTask = new FutureTask(runnable,"ok");
        futureTask.run();
        Object o = futureTask.get();
        System.out.println(o);
    }

}
