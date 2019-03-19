import cn.itcast.heima2.User;
import javaDemo.classloader.A;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Date 2019/1/12 16:53
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
        InterA ab = new ClassAB();
        ((InterB) ab).testb();
        System.out.println(ab instanceof InterB);
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