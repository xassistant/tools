package aop;

import java.io.InputStream;

public class AOPFrameTest {

	public static void main(String[] args) {
		InputStream in = AOPFrameTest.class.getResourceAsStream("config.properties");
	    Object bean = new BeanFactory(in).getBean("xxx");
	    System.out.println(bean.getClass().getName());
	}
}
