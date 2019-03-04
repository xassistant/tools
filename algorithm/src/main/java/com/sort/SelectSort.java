package com.sort;

import com.alibaba.fastjson.JSON;

/**
 * 1 选择排序
 * 已知一组无序数据a[1]、a[2]、……a[n]，需将其按升序排列。首先比较a[1]与a[2]的值，若a[1]大于a[2]则交换两者的值，否则不变
 * 。再比较a
 * [1]与a[3]的值，若a[1]大于a[3]则交换两者的值，否则不变。再比较a[1]与a[4]，以此类推，最后比较a[1]与a[n]的值。这样处理一轮后
 * ，a
 * [1]的值一定是这组数据中最小的。再将a[2]与a[3]~a[n]以相同方法比较一轮，则a[2]的值一定是a[2]~a[n]中最小的。再将a[3]与a[
 * 4]~a[n]以相同方法比较一轮，以此类推。共处理n-1轮后a[1]、a[2]、……a[n]就以升序排列了。
 * <p>
 * 优点：稳定，比较次数与冒泡排序一样； 缺点：相对之下还是慢。
 */

public class SelectSort {
    public static void main(String[] args) {
        sort1();
    }

    public static void sort1() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        for (int i = 0; i < arr.length; i++) {
            // 外层对内层比较数据大小
            for (int j = i + 1; j < arr.length; j++) {
                // 这里的<  >是排序用的,arr[i]常量值，arr[j]变量值,最终把变量值复制到常量值，所以用j=i+1
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        System.out.println(JSON.toJSONString(arr));
    }
}
