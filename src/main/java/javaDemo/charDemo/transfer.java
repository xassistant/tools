package javaDemo.charDemo;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * char是Java的字符类型。每char有2个字节，采用Unicode字符集标准，并在计算机中用UTF-16编码算法存储。
 * 
 * @author admin
 *
 */
public class transfer {
	// 转化字符串str为指定编码方式cstr的存储字节组
	public void transfer(String str, String cstr) {
		Charset cs = Charset.forName(cstr);
		byte[] bs = cs.encode(CharBuffer.wrap(str)).array();
		printBytes(bs); // 打印字节序列
	}

	private void printBytes(byte[] bs) {
		for (int i = 0; i < bs.length; i++) {
			System.out.println(bs[i]);
		}
		System.out.println();
	}

	@Test
	public void Test() {
		transfer("我", "UTF-16"); // utf-16编码字节序列：0x62 0x11
		transfer("我", "UTF-8"); // utf-8编码字节序列：0xe6 0x88 0x91
		transfer("我", "GBK"); // gbk编码字节序列：0xce 0xd2
		transfer("我", "GB2312"); // gb2312编码字节序列：0xce 0xd2
		transfer("我", "ISO-8859-1"); // iso 8859-1编码字节序列：0x3f
	}

	/************************************************************************************/
	@Test
	public void test3() {
		// '我'的UTF-8编码的字符序列
		byte[] utf8Bytes = { (byte) -26, (byte) -120, (byte) -111 };
		// 采用平台的默认编码方式解码指定的utf8Bytes数组，形成一个新的String
		System.out.println(new String(utf8Bytes));
		// 采用UTF-8编码方式解码指定的utf8Bytes数组，形成一个新的String
		System.out.println(new String(utf8Bytes, Charset.forName("UTF-8")));

		byte[] bytes = null;
		try {
			bytes = new String(utf8Bytes, "UTF-8").getBytes();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		printBytes(bytes);
		System.out.println(new String(bytes));
	}

	/************************************************************************************/
	@Test
	public void test2() {

		String str = "我";

		printBytes(str.getBytes(Charset.forName("UTF-16")));
		printBytes(str.getBytes(Charset.forName("UTF-8")));
	}

	/************************************************************************************/
	@Test
	public void test1() {
		Charset charset = Charset.forName("Utf-8");
		System.out.println("是否可用: " + charset.canEncode());
		// 查看别名
		Set<String> set = charset.aliases();
		Iterator<String> iterator = set.iterator();
		System.out.println("-------UTF-8的别名-------------");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		// 编码1
		System.out.println("-------编码1-------------");
		ByteBuffer buffer = charset.encode("hello");
		System.out.println(buffer);
		System.out.println("缓冲区剩余: " + buffer.remaining());
		// 打印编码后的字符串
		while (buffer.hasRemaining()) {
			System.out.print((char) buffer.get());
		}
		System.out.println("\n缓冲区剩余: " + buffer.remaining());
		// 解码
		System.out.println("-------解码-------------");
		buffer.flip();
		CharBuffer charBuffer = charset.decode(buffer);
		System.out.println(charBuffer.toString());

	}
}
