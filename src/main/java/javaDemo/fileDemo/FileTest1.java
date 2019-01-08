package javaDemo.fileDemo;

import org.junit.Test;

import java.io.File;

/**
 * @Author xlj
 * @Date 2019/1/7 16:08
 */
public class FileTest1 {
    @Test
    public void test() {
        File file = new File("./test");
        System.out.println(file.getAbsolutePath());
    }
}
