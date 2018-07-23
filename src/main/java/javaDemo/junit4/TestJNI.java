package javaDemo.junit4;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixMethodOrder(MethodSorters.JVM) // 指定测试方法按定义的顺序执行
public class TestJNI {

	private static final Logger logger = LoggerFactory.getLogger(TestJNI.class);

	@Test
	public void testAddAndGet() {
		logger.info("test 'addBean' and 'getBean' ");
	}

	@Test
	public final void testSearch() {
		logger.info("test search CODE from JNI memory...");
	}

	@Test
	public final void testRemove() {
		logger.info("test remove CODE from JNI memory...");
	}
}