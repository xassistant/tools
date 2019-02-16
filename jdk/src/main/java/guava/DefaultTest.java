package guava;

import com.google.common.base.Defaults;
import org.junit.Test;

/**
 * Defaults类提供Java各原生类型的默认值
 *
 * com.google.common.base.Defaults.defaultValue(Class type)
 *
 * boolean.class                         //返回false
 * char.class                            //返回'\0'
 * byte.class                            //返回(byte)0
 * short.class                           //返回(short)0
 * int.class                             //返回0
 * long.class                            //返回0L
 * float.class                           //返回0f
 * double.class                          //返回0d
 * non-primitive types                   //返回null
 */
public class DefaultTest {
    @Test
    public void test1(){
        Integer integer = Defaults.defaultValue(Integer.class);
        System.out.println(integer);
        Integer integer1 = Defaults.defaultValue(int.class);
        System.out.println(integer1);
    }
}
