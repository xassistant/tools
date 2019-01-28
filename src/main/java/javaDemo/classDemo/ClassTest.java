package javaDemo.classDemo;

import org.junit.Test;

public class ClassTest {

    @Test
    public void ClassTest() throws ClassNotFoundException {
        //获取类的字节码的三种方法：
        //1.使用Class.class
        Class<?> c1 = String.class;
//        2.使用实例.getClass()
        String s = new String("1");
        Class<?> c2 = s.getClass();
//        3.使用Class.forName("类全名");
        Class<?> c3 = Class.forName("java.lang.String");
        System.out.println(c3 ==String.class);//true
        System.out.println(c1 == c2);//true
        System.out.println(c2 == c3);//true
        System.out.println(c1 == c3);//true
//        获取基本类型的字节码
        System.out.println(int.class);//int
        //基本类型的字节码和其包装类是不一样的
        System.out.println(Integer.class == int.class);//false
        //判断是不是基本类型
        System.out.println(c1.isPrimitive());//false
        //判断是不是数组
        System.out.println(String[].class.isArray());//true
//        判断是不是int 基本类型
        System.out.println(int.class == Integer.TYPE);// true
        Integer integer = new Integer(1);
        System.out.println(integer.getClass() == int.class);//false
        System.out.println(integer.getClass() == Integer.TYPE);//false
        System.out.println(integer.getClass() == Integer.class);//true
    }
}