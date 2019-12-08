import javaDemo.classloader.A;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2019/1/12 16:53
 */
public class Test {
    public static void main(String[] args) {
        PorxyInterfaceTest instance = (PorxyInterfaceTest) Proxy.newProxyInstance(PorxyInterfaceTest.class.getClassLoader(),
                new Class[]{PorxyInterfaceTest.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        ProxyClass proxyClass = new ProxyClass();
                        System.out.println("before");
                        method.invoke(proxyClass, args);
                        System.out.println("end");
                        return null;
                    }
                });
        instance.test();

    }

    interface PorxyInterfaceTest {
        void test();
    }

    static class ProxyClass implements PorxyInterfaceTest {

        @Override
        public void test() {
            System.out.println("aaa");
        }
    }

}