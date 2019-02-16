package javaDemo.ioDemo;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @Date 2019/1/18 10:28
 */
public class ByteBufferTest {
    @Test
    public void byteByfferFlipTest() throws UnsupportedEncodingException {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        byte[] bytes = "Test".getBytes("UTF-8");
        System.out.println(bytes);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        byte[] conver = conver(byteBuffer);
        conver[0] = 10;
        System.out.println(Arrays.toString(conver) + ":" + Arrays.toString(bytes));

        byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        System.out.println(Arrays.toString(conver(byteBuffer)));
    }

    /**
     * 包装操作创建一个缓冲区对象但是不分配任何空间来储存数据元素。
     * 它使用您所提供的数组作为存储空间来储存缓冲区中的数据元素
     */
    @Test
    public void testWrap() {
        byte[] bytes = new byte[6];
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        /**
         * 对缓冲区的修改会影响数组，对数组的修改同样会影响缓冲区的数据
         */
        bb.put((byte) 'h').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o').put((byte) '!');
        bb.flip();
        for (byte b : bytes) {
            System.out.print((char) b);          //结果hello!
        }
        System.out.println();
        bytes[0] = (byte) 'a';       //改变数组第0项
        while (bb.hasRemaining()) {
            System.out.print((char) bb.get());   //结果aello!
        }
        /**
         * 带参数的包装方法
         */
        ByteBuffer bp = ByteBuffer.wrap(bytes, 2, 2);
        /**
         * 带参数的包装方法wrap(array, offset, length)并不意味着取数组的子集来作为缓冲区，
         * offset和length属性只是设置了缓冲区初始状态；上面代码表示创建了posion为2，limit为4，
         * 容量为bytes.length的缓冲区
         */

    }

    //必须调用完后flip()才可以调用此方法
    public byte[] conver(ByteBuffer byteBuffer) {
        int len = byteBuffer.limit() - byteBuffer.position();
        byte[] bytes = new byte[len];

        if (byteBuffer.isReadOnly()) {
            return null;
        } else {
            byteBuffer.get(bytes);
        }
        return bytes;
    }

    /**
     * 通过allocate和wrap方法创建的缓冲区都是间接缓冲区，
     * 间接缓冲区中使用备份数组，对于缓冲区备份数组java也提供了一些api
     */
    @Test
    public void testBufferArray() {
        byte[] bytes = new byte[6];
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        if (bb.hasArray())           //hasArray()方法判断缓冲区是否有备份数组
        {
            byte[] byteArr = bb.array();            //array()方法能够取得备份数组
            System.out.println(bytes == byteArr);
            System.out.println(bb.arrayOffset());   //arrayOffset()方法返回缓冲区数据在数组中可以存储的开始位置
        }

        /**
         * 能够获得缓冲区的备份数组就获得了对缓冲区进行存取的权限，当缓冲区被设为只读的时候，
         * 无疑是不允许得到备份数组的。
         */
        ByteBuffer bRead = bb.asReadOnlyBuffer();
        System.out.println(bRead.hasArray());   //输出为false
    }

    /**
     * Duplicate()方法创建了一个与原始缓冲区相似的新缓冲区，
     * 两个缓冲区共享数据元素，对一个缓冲区数据的修改将会反映在另一个缓冲区上，
     * 但每个缓冲区拥有自己独立的position、limit、mark属性，
     * 如果原始缓冲区是只读的或者直接缓冲区，复制的缓冲区将继承这些属性。
     */
    @Test
    public void testDuplicate() {
        ByteBuffer orginal = ByteBuffer.allocate(8);
        orginal.position(3).limit(7).mark().position(5);
        ByteBuffer duplicate = orginal.duplicate();
        orginal.clear();
        System.out.println("orginal，position: " + orginal.position() + "; limit: " + orginal.limit() + "; mark: " + orginal.position());       //结果 orginal，position: 0; limit: 10; mark: 0
        System.out.println("duplicate，position: " + duplicate.position() + "; limit: " + duplicate.limit() + "; mark: " + duplicate.reset().position());     //结果 duplicate，position: 5; limit: 8; mark: 3
        //前面提到的asReadOnlyBuffer方法得到的只读缓冲区同duplicate类似
    }
    /**
     * slice方法将对缓冲区进行分割，从原始缓冲区当前位置开始，直到上限
     * 也就是position到limit的区间创建了新的缓冲区，新缓冲区和原始缓冲区共享一段数据元素，
     * 也会继承只读和直接属性。
     */
    public void testSlice()
    {
        ByteBuffer orginal = ByteBuffer.allocate(10);
        orginal.position(3).limit(8);
        ByteBuffer slice = orginal.slice();     //分割了3-8的数据元素
    }
}
