package javaDemo.proxyDemo.dbProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by zxb on 2017/4/22
 */
public class DBQueryProxy2 implements MethodInterceptor {
 
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Here in interceptor 2！");
        return methodProxy.invokeSuper(o, objects);
    }
}