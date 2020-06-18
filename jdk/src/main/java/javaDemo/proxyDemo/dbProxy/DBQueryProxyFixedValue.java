package javaDemo.proxyDemo.dbProxy;

import org.springframework.cglib.proxy.FixedValue;

/**
 * 返回固定的值
 * Create by zxb on 2017/4/23
 */
public class DBQueryProxyFixedValue implements FixedValue {
 
    @Override
    public Object loadObject() throws Exception {
        System.out.println("Here in DBQueryProxyFixedValue ! ");
        return "Fixed Value";
    }
}