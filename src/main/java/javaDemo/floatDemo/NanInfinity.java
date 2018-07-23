package javaDemo.floatDemo;

import org.junit.Test;

public class NanInfinity {
	@Test
	public void test1() {
		// Infinity表示无穷大
		// NaN 不是一个数字（Not a Number）
		double i = 1.0 / 0;
		System.out.println(i); // Infinity
		System.out.println(i + 1); // Infinity
		System.out.println(i == i + 1); // true

		i = 0.0 / 0;
		System.out.println(i); // NaN
		System.out.println(i + 1); // NaN
		System.out.println(i == i + 1); // false
	}
}
