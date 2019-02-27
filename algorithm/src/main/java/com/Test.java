package com;

/**
 * @Date 2019/2/26 16:53
 */
public class Test {
    public static void main(String[] args) {
         AAAA a =  new AAAA();
        AAAA b = new AAAA();
    }
}

class AAAA {
    static {
        System.out.println("static ");
    }

    {
        System.out.println(a +":"+c);
        System.out.println("初始化块");
    }

    public AAAA() {
        System.out.println(a +":"+c+":"+b);
        System.out.println("初始化");
    }

    public static final String a = "aaa";
    public final String b = "bbb";
    public static String c = "ccc";

    public static void test() {
        System.out.println("test 方法");
    }
}