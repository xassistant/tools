package javaDemo.proxyDemo;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo implements InvocationHandler {

    static ProxyDemo proxyDemo = new ProxyDemo();

    public ProxyDemo() {
    }

    private Object sub;

    public ProxyDemo(Object obj) {
        sub = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被调用代理对象的方法： " + method.getName() + ":" + method.getDeclaringClass());
        System.out.println("before method !!!!!");
        Object invoke = method.invoke(sub, args);
        System.out.println("after method !!!!!");
        return invoke;
    }

    public <T> T getProxy(T t) {
        ProxyDemo handler = new ProxyDemo(t);
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), handler);
    }


    public static void main(String[] args) {
        PersonDao pDao = new PersonDaoImpl();
        PersonDao proxy = proxyDemo.getProxy(pDao);
        proxy.say();
        System.out.println("完成");

        // 获取代理字节码文件，
        Class<?>[] interfaces = new Class[]{PersonDao.class};
        byte[] bytes = ProxyGenerator.generateProxyClass("PersonDao", interfaces);
        File file = new File("./jdk/src/main/java/javaDemo/proxyDemo/PersonDao.class");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

interface PersonDao {
    void say();
}

class PersonDaoImpl implements PersonDao {

    @Override
    public void say() {
        System.out.println("hello boy");
    }
}