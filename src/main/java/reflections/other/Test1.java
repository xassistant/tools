package reflections.other;

import org.junit.Test;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @Author xlj
 * @Date 2018/12/24 9:31
 */
public class Test1 {
    @Test
    public void test1(){
        Set<Field> fields = ReflectionUtils.getFields(Test1.class);
    }
}
