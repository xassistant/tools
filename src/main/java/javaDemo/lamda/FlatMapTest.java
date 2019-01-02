package javaDemo.lamda;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author xlj
 * @Date 2018/12/25 9:36
 */
public class FlatMapTest {
    @Test
    public void test1() {
        String[] strings = {"Hello", "World"};
        List<String> collect = Arrays.stream(strings).map(e -> e.split("")).flatMap(e -> Stream.of(e)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));

    }
}
