package com.jvm;

/**
 * @Date 2019/2/27 8:56
 * javap -v Test.class 查看及字节码
 */
public class Test {
    static {
        System.out.println("类初始化-静态代码块");
    }

    {
        System.out.println("初始化块");
    }

    public Test() {
        System.out.println("实例初始化-构造方法");
    }

    public static void main(String[] args) {
        Test.test();
        synchronized (Test.class) {

        }
        System.out.println();
    }

    private static void test() {

    }
}
