package javaDemo.cloneDemo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @Author xlj
 * @Date 2019/1/2 10:48
 */
public class ArrCloneDemo {
    @Test
    public void test() {
        String[] arr = {"1", "a"};
        String[] clone = arr.clone();
        clone[0] = "1212";

        System.out.println(JSON.toJSONString(arr));
        System.out.println(JSON.toJSONString(clone));
    }
}
