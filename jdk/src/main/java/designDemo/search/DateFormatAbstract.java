package designDemo.search;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xlj
 * @Date 2018/9/30 11:23
 */
public abstract class DateFormatAbstract {
    public String string = "";

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}

class Son {
    private static Son instance = null;

    private Son() {
        System.out.println(Thread.currentThread().getName() + "  Son()");
    }

    public static Son getInstance() {
        if (instance == null) {
            System.out.println(Thread.currentThread().getName() + " getInstance()");
            synchronized (Son.class) {
                if (instance == null) {
                    instance = new Son();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new Thread(() -> Son.getInstance()).start();
        new Thread(() -> Son.getInstance()).start();
        new Thread(() -> Son.getInstance()).start();
    }
}