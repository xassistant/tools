package com.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组a[1...n]中，把后一个和前面排序好的进行比较交换，小于就与所比较的值进行交换
 * <p>
 * 优点：稳定，快； 缺点：比较次数不一定，比较次数越少，插入点后的数据移动越多，特别是当数据总量庞大的时候，但用链表可以解决这个问题。
 */
public class DirectInsertionSort {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        int tmp = 0;
        // 直接插入排序,a[i]和前边的排序好的进行比较交换
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }

        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
