package com.yongyou;

import org.junit.Test;

/**
 * @Date 2019/2/16 14:39
 * 1：用友面试题，java对象在虚拟机中的大小是否可以sizeof得到
 * 2：匿名内部类是否可以继承其他类，实现其他接口
 */
interface AnonymousInterface {
    void test();
}

interface AnonymousInterfaceTest {

}

class AnonymousClassTest {

}

public class YongYou {
    /**
     * 回答：匿名内部类在实现时必须借助一个接口或者一个抽象类或者一个普通类来构造，
     * 从这过层次上讲匿名内部类是实现了接口或者继承了类，
     * 但是不能通过extends或implement关键词来继承类或实现接口。
     */
    @Test
    public void test() {
        AnonymousInterface anonymousInterface = new AnonymousInterface() {
            @Override
            public void test() {

            }
        };

    }
}
