package javaDemo.forDemo;

import org.junit.Test;

/**
 * @Author xlj
 * @Date 2018/12/29 13:36
 */
public class ForTest {
    @Test
    public void test1() throws InterruptedException {
        for (;;) {
            System.out.println(11);
            Thread.sleep(1000);
        }
    }
}
