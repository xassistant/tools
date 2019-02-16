package javaDemo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logDemo {
	static Logger _log = LoggerFactory.getLogger(logDemo.class);

	public static void main(String[] args) {
		// 测试sysout
		long start = System.currentTimeMillis();

		for (int i = 0; i < 10000; i++) {
			System.out.println("Syso输出" + i);
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("system.out:" + time);
		// 测试log框架
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			_log.info("log输出" + i);
		}
		long time1 = System.currentTimeMillis() - start1;

		_log.info("所用时间:" + time1);
	}

}
