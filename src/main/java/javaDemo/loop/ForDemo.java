package javaDemo.loop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ForDemo {
	/**
	 * 界限500000 ，如果小于500000，用第二种，大于500000，则普通方法即可
	 */
	@Test
	public void test00() {
		List<String> list = new ArrayList<String>();
		for (int j = 0; j < 500000; j++) {
			list.add("a");
		}
		long start = System.currentTimeMillis();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.println(list.get(i));
		}
		long end = System.currentTimeMillis();
		long start1 = System.currentTimeMillis();
		for (int ii = 0, len = list.size(); ii < len; ii++) {
			System.out.println(list.get(ii));
		}
		System.out.println(end - start);
		System.out.println(System.currentTimeMillis() - start1);
		// 10000数据量
		// 116
		// 61
		// 500000数据量
		// 2091
		// 2150
		// 600000数据量
		// 2381
		// 2555
	}
}
