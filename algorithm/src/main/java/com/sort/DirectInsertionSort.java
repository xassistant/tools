package com.sort;

import org.junit.Test;

/**
 * 数组a[1...n]中，把后一个和前面排序好的进行比较交换，小于就与所比较的值进行交换
 * <p>
 * 优点：稳定，快； 缺点：比较次数不一定，比较次数越少，插入点后的数据移动越多，特别是当数据总量庞大的时候，但用链表可以解决这个问题。
 */
public class DirectInsertionSort {

    @Test
    public void insertSort1() {
        // 直接插入排序,a[i]和前边的排序好的进行比较交换
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //循环层
        for (int i = 1; i < a.length; i++) {
            // 插入值的索引
            int index = i;
            // 插入值
            int insertValue = a[i];
            // 这里使用for进行循环所有排序好的值与插入值比较
            // 排序好的值由右到左进行比较
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > insertValue) {
                    // 比较交换
                    a[index] = a[j];
                    a[j] = insertValue;
                }
                // 插入索引前置移动，逐个匹配以排序好的值
                index--;
            }
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    @Test
    public void insertSort2() {
        // 直接插入排序,a[i]和前边的排序好的进行比较交换
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        for (int i = 1; i < a.length; i++) {
            int index = i - 1;
            int insertValue = a[i];
            // 这里使用while循环，对于数值循环条件来说
            // 细品一下，for的循环自带缩减或者增加
            // while使用的时候需要另写缩减或者增加条件
            while (index >= 0) {
                if (a[index] > insertValue) {
                    a[index + 1] = a[index];
                    a[index] = insertValue;
                }
                index--;
            }
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }


    @Test
    public void insertSort3() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            // insertVal准备和前一个数比较
            int index = i - 1;
            // 这里相较于insertSort2的while来说，合并了if到while里边
            while (index >= 0 && insertVal < arr[index]) {
                // 将把arr[index]向后移动
                arr[index + 1] = arr[index];
                // 让index 向前移动
                index--;
            }
            // 将insertVal 插入到适当的位置
            arr[index + 1] = insertVal;
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 二分插入排序
     */
    @Test
    public void binarySort() {
        Object[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        int lo = 0;
        for (int start = 1; start < a.length; start++) {
            Comparable pivot = (Comparable) a[start];

            int left = lo;
            int right = start;
            while (left < right) {
                // 二分插入排序获取中值
                int mid = (left + right) >>> 1;
                if (pivot.compareTo(a[mid]) < 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int n = start - left;
            switch (n) {
                case 2:
                    a[left + 2] = a[left + 1];
                case 1:
                    a[left + 1] = a[left];
                    break;
                default:
                    System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
