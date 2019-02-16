package guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * @Author xlj
 * @Date 2018/12/25 10:29
 */
public class PreconditionsTest {
    @Test
    public void test1(){
        Preconditions.checkNotNull(null,"aaa","param");
    }
}
