package javaDemo.ThreadDemo;

import java.util.concurrent.*;

/**
 * @Author xlj
 * @Date 2019/1/5 11:23
 */
public class ThreadPollTest1 {
    private static class Worker implements Runnable {
        Integer i;

        public Worker(int i) {
            this.i = i;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running" + "(当前线程数：)" + Thread.activeCount() + "---编号任务：" + i);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is over!!!" + "(当前线程数：)" + Thread.activeCount());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 5;
//        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        //拒绝策略1：将抛出 RejectedExecutionException.
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, handler);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("自定义策略--");
//                r.run();
            }
        });

        for (int i = 0; i < 100; i++) {
            executor.execute(new Worker(i));
            System.out.println("=================================");
        }
        executor.shutdown();

    }
}
