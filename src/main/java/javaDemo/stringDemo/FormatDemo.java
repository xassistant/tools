package javaDemo.stringDemo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import ch.qos.logback.core.net.SyslogOutputStream;

public class FormatDemo {
	@Test
	public void test1() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 3000; i++) {
			System.out.println(String.format("您提供的作业配置信息有误, String:[%s] 不允许重复出现在列表中: [%s].", "如果全是爱", "ArrayList"));
		}
		long end = System.currentTimeMillis();

		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			System.out.println("您提供的作业配置信息有误, String:" + "如果全是爱" + " 不允许重复出现在列表中: " + "ArrayList" + ".");
		}
		System.out.println(end - start);
		System.out.println(System.currentTimeMillis() - start1);
	}

	// 10000数据量
	// 294
	// 139
	// 100数据量
	// 11
	// 169
	// 3000数据量
	// 146
	// 126
	@Test
	public void test2() {
		String str = null;
		str = String.format("Hi,%s", "飞龙"); // 格式化字符串
		System.out.println(str); // 输出字符串变量str的内容
		System.out.printf("字母a的大写是：%c %n", 'A');
		System.out.printf("3>7的结果是：%b %n", 3 > 7);
		System.out.printf("100的一半是：%d %n", 100 / 2);
		System.out.printf("100的16进制数是：%x %n", 100);
		System.out.printf("100的8进制数是：%o %n", 100);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');
	}
}
