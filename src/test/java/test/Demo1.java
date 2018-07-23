package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import com.alibaba.dubbo.common.serialize.support.dubbo.Builder;

public class Demo1 {
	protected Demo1() {

	}

	@Test
	public void test() {
		System.out.println(new PhyOSStatus());
		System.out.println((String) null);
		System.out.println(StringUtils.join(new String[] { "a", "plugin" }, File.separator));
		{
			System.out.println("方法里边的括号什么意思呢？,看了class文件，没有个鸟作用");
			{
				int i = 100;
				System.out.println(i);

			}
		}
		////////////////////////////////////////
		Class<?>[] clazz = new Class<?>[] { int.class, String.class };
		StringBuilder sb = new StringBuilder();
		for (Class<?> s : clazz)
			sb.append(s);
		String join = StringUtils.join(clazz);
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 100; i++) {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

				}
			});
		}
		newFixedThreadPool.shutdown();
		System.out.println("bbbbbbbbbbbbbbb");
	}
}

class PhyOSStatus {
	static final long GB = 1024 * 1024 * 1024;
	long totalPhysicalMemory = -1;

	public String toString() {
		System.out.println("\tasd\n");
		return String.format("\ttotalPhysicalMemory:\t%.2fG\n", (float) totalPhysicalMemory / GB);
	}
}