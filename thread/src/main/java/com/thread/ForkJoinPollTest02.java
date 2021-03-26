package com.thread;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

class ForkJoinSumCalulate extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    /**
     * 临界值
     */
    public static final int THRESHOLD = 5;

    public ForkJoinSumCalulate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinSumCalulate left = new ForkJoinSumCalulate(start, middle);
            left.fork();
            ForkJoinSumCalulate right = new ForkJoinSumCalulate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}

public class ForkJoinPollTest02 {

    /**
     * 1、ForkJoin实现计算
     */
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSumCalulate task = new ForkJoinSumCalulate(1L, 100L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }

    /**
     * 2、普通的for循环
     */
    @Test
    public void test1() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0L; i <= 100L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }


    /**
     * 3、使用jdk8的API
     */
    @Test
    public void test2() {
        Instant start = Instant.now();
        Long sum = LongStream.rangeClosed(0L, 100L)
                .parallel()
                .reduce(0, Long::sum);

        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }
}
