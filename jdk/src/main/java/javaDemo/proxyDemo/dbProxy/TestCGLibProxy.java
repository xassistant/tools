package javaDemo.proxyDemo.dbProxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class TestCGLibProxy {

    public static void main(String[] args) {
        DBQueryProxy dbQueryProxy = new DBQueryProxy();
        DBQueryProxy2 dbQueryProxy2 = new DBQueryProxy2();
        Callback noopCb = NoOp.INSTANCE;
        Callback fixedValue = new DBQueryProxyFixedValue();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DBQuery.class);
        enhancer.setCallbacks(new Callback[]{dbQueryProxy, dbQueryProxy2, noopCb, fixedValue});
        enhancer.setCallbackFilter(method -> {
            if (method.getName().equals("getElement")) {
                return 0;
            } else if (method.getName().equals("getAllElements")) {
                return 1;
            } else if (method.getName().equals("methodForNoop")) {
                return 2;
            } else if (method.getName().equals("methodForFixedValue")) {
                return 3;
            } else {
                return 0;
            }
        });
        DBQuery dbQuery = (DBQuery) enhancer.create();
        System.out.println("========Inteceptor By DBQueryProxy ========");
        System.out.println(dbQuery.getElement("Hello"));
        System.out.println();
        System.out.println("========Inteceptor By DBQueryProxy2 ========");
        System.out.println(dbQuery.getAllElements());
        System.out.println();
        System.out.println("========Return Original Value========");
        System.out.println(dbQuery.methodForNoop());
        System.out.println();
        System.out.println("========Return Fixed Value========");
        System.out.println(dbQuery.methodForFixedValue("myvalue"));
    }
}