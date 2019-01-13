package aop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    Properties properties = new Properties();
	public BeanFactory(InputStream in){
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object getBean(String name){
		Object bean = null;
		String className = properties.getProperty(name);
		try {
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(bean instanceof ProxyFactoryBean){
			Object proxy = null;
			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)bean;
			try {
				Advice advice = (Advice)Class.forName(properties.getProperty(name + ".advice")).newInstance();
				Object target = Class.forName(properties.getProperty(name + ".target")).newInstance();
				proxyFactoryBean.setAdvice(advice);
				proxyFactoryBean.setTarget(target);
				proxy = proxyFactoryBean.getProxy();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return proxy;
		}
		return bean;
	}
}
