import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


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

//    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
//        InterA ab = new ClassAB();
//        ((InterB) ab).testb();
//        System.out.println(ab instanceof InterB);
//    }
}

interface PorxyInterfaceTest {
    void test();
}

class ProxyClass implements PorxyInterfaceTest {

    @Override
    public void test() {
        System.out.println("aaa");
    }
}

class ClassAB extends AbstrackTestClass implements InterB {

    @Override
    public void testb() {
        System.out.println("bbb");
    }
}

abstract class AbstrackTestClass implements InterA {
    @Override
    public void testa() {
        System.out.println("aaa");
    }
}

interface InterA {
    void testa();
}

interface InterB {
    void testb();
}