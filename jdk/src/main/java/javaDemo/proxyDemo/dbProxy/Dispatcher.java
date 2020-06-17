package javaDemo.proxyDemo.dbProxy;

import org.springframework.cglib.proxy.Callback;

public interface Dispatcher extends Callback {
    Object loadObject() throws Exception;
}