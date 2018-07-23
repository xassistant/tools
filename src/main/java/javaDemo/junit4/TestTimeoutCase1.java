package javaDemo.junit4;

import org.junit.Test;

public class TestTimeoutCase1 {

	@Test
	public void hello() {
		System.out.println("正常测试");
	}

	@Test(timeout = 5000)
	public void helloTooSlow() {
		System.out.println("模拟超时测试");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}