package javaDemo.pathsDemo;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author xlj
 * @Date 2019/1/7 9:36
 */
public class pathsTest1 {
    @Test
    public void test1(){
        Path path = Paths.get("F:/simsun.ttc");
        String s = path.getParent().toString();
        System.out.println(s);
    }
}
