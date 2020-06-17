package javaDemo.proxyDemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class CGlibTestClass {
    public final void test1() {
        System.out.println("hello world test1");
    }

    public void test2() {
        System.out.println("hello world test2");
    }


    public static void main(String[] args) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGlibTestClass.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("before method run...");
            Object result = proxy.invokeSuper(obj, args1);
            System.out.println("after method run...");
            return result;
        });
        CGlibTestClass sample = (CGlibTestClass) enhancer.create();
        sample.test1();
        sample.test2();
        // 获取代理字节码文件
        byte[] bytes = enhancer.getStrategy().generate(enhancer);
        File file = new File("./jdk/src/main/java/javaDemo/proxyDemo/CGlibTestClass.class");
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