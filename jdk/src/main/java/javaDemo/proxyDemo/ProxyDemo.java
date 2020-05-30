package javaDemo.proxyDemo;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo implements InvocationHandler {
    private Object sub;

    public ProxyDemo() {
    }

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

    public <T> T getProxy(T t) {
        ProxyDemo handler = new ProxyDemo(t);
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(),
                t.getClass().getInterfaces(), handler);
    }


    public static void main(String[] args) {
        ProxyDemo proxyDemo = new ProxyDemo();
        PersonDao pDao = new PersonDaoImpl();
        PersonDao proxy = proxyDemo.getProxy(pDao);
        proxy.say();
        System.out.println("完成");
    }

    private Object createInterfaceProxyForFactoryBean(final Object factoryBean, Class<?> interfaceType,
                                                      final ConfigurableBeanFactory beanFactory, final String beanName) {

        return Proxy.newProxyInstance(
                factoryBean.getClass().getClassLoader(), new Class<?>[] {interfaceType},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("getObject") && args == null) {
                            return beanFactory.getBean(beanName);
                        }
                        return ReflectionUtils.invokeMethod(method, factoryBean, args);
                    }
                });
    }

}

interface PersonDao {
    void say();
}

class PersonDaoImpl implements PersonDao {

    @Override
    public void say() {
        System.out.println("time to eat");
    }

}