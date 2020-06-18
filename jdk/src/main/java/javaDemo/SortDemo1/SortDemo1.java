package javaDemo.SortDemo1;

import org.junit.Test;

import java.util.*;

public class SortDemo1 {
    @Test
    public void printListStringSort() {
        List<String> list = new ArrayList<>();
        list.add("15");
        list.add("1");
        list.add("12");
        list.add("123");
        list.add("10");
        list.add("9");
        list.add("3");

        // 常规的排序,默认二分排序
        Collections.sort(list);

        // 打印出数据
        for (String str : list) {
            System.out.print(str + "  ");
        }
    }

    @Test
    public void printListStringSort1() {
        List<String> list = new ArrayList<String>();
        list.add("15");
        list.add("123");
        list.add("10");
        list.add("9");
        list.add("3");

        System.out.println("---------------升序排列----------------");
        // 升序排列
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num1 = Integer.parseInt(o1);
                int num2 = Integer.parseInt(o2);
                if (num1 > num2) {
                    return 1;
                } else if (num1 < num2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("---------------降序排列----------------");
        // 降序排列
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num1 = Integer.parseInt(o1);
                int num2 = Integer.parseInt(o2);
                if (num1 < num2) {
                    return 1;
                } else if (num1 > num2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (String str : list) {
            System.out.println(str);
        }
    }
}
