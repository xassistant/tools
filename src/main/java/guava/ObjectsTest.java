package guava;

import cn.itcast.heima2.User;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author xlj
 * @Date 2018/12/25 10:37
 */
public class ObjectsTest {
    @Test
    public void test1() {

        assertEquals(true, Objects.equal("", ""));
        assertEquals(false, Objects.equal(null, ""));
        System.out.println(Objects.hashCode(1, 2, 3, 4, 45));

        String s = Objects.toStringHelper(User.class)
                .add("name", "wangge")
                .toString();
        System.out.println(s);

        int compare = Ints.compare(1, 2);
        String s1 = Strings.commonPrefix("dfabb", "aaaccc");
        System.out.println(s1);
    }

    /**
     * ComparisonChain是一个lazy的比较过程， 当比较结果为0的时候，
     * 即相等的时候， 会继续比较下去， 出现非0的情况， 就会忽略后面的比较
     */
    @Test
    public void test2() {
        int result = ComparisonChain.start()
                .compare(1, 1)
                .compare("a", "a")
                .result();
        System.out.println(result);
    }
}
