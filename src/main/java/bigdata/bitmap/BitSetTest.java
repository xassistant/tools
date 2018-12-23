package bigdata.bitmap;

import org.junit.Test;

import java.util.BitSet;

/**
 * @Author xlj
 * @Date 2018/12/14 9:24
 */
public class BitSetTest {
    @Test
    public void bitsetTest1() {
        int[] array = new int[]{1, 2, 90, 22, 0, 3};
        BitSet bitSet = new BitSet(6);
        //将数组内容组bitmap
        for (int i = 0; i < array.length; i++) {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.size());
        System.out.println(bitSet.get(3));

    }
}
