package com.sort;

/**
 * @Date 2019/2/16 15:28
 */
public class BubbleSort {
    public static void main(String[] args) {

        sort();
    }

    /**
     *
     *   3、冒泡排序 已知一组无序数据a[1]、a[2]、……a[n]，需将其按升序排列。首先比较a[1]与 a[2]的值，若a[1]大于a[2]则交换
     *   两者的值，否则不变。再比较a[2]与a[3]的值，若a[2]大于a[3]则交换两者的值，否则不变。再比 较a[3]与a[4]，以此
     *   类推，最后比较a[n-1]与a[n]的值。这样处理一轮后，a[n]的值一定是这组数据中最大的。再对a[1]~a[n- 1]以相同方法
     *   处理一轮，则a[n-1]的值一定是a[1]~a[n-1]中最大的。再对a[1]~a[n-2]以相同方法处理一轮，以此类推。共处理 n-1 轮
     *   后a[1]、a[2]、……a[n]就以升序排列了。
     *
     *   优点：稳定； 缺点：慢，每次只能移动相邻两个数据。
     */

    public static void sort() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};

        int tmp = 0;
        // i是决定走几趟, 冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环，开始逐个比较，如果发现前一个数比后一个数大就交换
            // 相邻的数据进行比较, 找到大的排到后边
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println("排序之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
