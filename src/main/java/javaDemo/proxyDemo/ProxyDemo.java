package javaDemo.proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo implements InvocationHandler {
	private Object sub;

	public ProxyDemo(Object obj) {
		sub = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("被调用代理对象的方法： " + method.getName() + ":" + method.getDeclaringClass());
		System.out.println("before method !!!!!");
		method.invoke(sub, args);
		System.out.println("after method !!!!!");
		return null;
	}

	public static void main(String[] args) {
		PersonDao pDao = new PersonDaoImpl();
		ProxyDemo handler = new ProxyDemo(pDao);

		PersonDao proxy = (PersonDao) Proxy.newProxyInstance(pDao.getClass().getClassLoader(),
				pDao.getClass().getInterfaces(), handler);
		proxy.say();

	}
}

interface PersonDao {
	public void say();
}

class PersonDaoImpl implements PersonDao {

	@Override
	public void say() {
		System.out.println("time to eat");
	}

}