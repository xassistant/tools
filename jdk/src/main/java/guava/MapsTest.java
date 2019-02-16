package guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @Author xlj
 * @Date 2018/12/24 10:09
 */
public class MapsTest {
    @Test
    public void test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", 1);
        jsonObject.put("a", 12);
        Map<String, String> dataSource = Maps.transformValues(jsonObject, Functions.toStringFunction());
    }
}