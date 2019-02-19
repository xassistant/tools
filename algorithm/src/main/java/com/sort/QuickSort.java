package com.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @Date 2019/2/19 15:29
 * 快速排序，找到一个基值，默认是第一个值，第一步：把基值赋值交换元素，while left小于right，
 * 从right到left查找比基值小的和left交换
 * 从left到right查找比基值大的和right交换
 * <p>
 * 最后把交换元素
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 5, 7, 8, 3, 1, 2, 9};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pos = quickSort(arr, low, high);
            sort(arr, low, pos - 1);
            sort(arr, pos + 1, high);
        }

    }

    private static int quickSort(int[] arr, int low, int high) {
        int base = arr[low];
        // 这个while是循环迭代
        while (low < high) {
            // 这俩里边的while是条件循环查找
            while (low < high && arr[high] >= base) {
                --high;
            }
            arr[low] = arr[high];
            System.out.println(JSON.toJSONString(arr));
            while (low < high && arr[low] <= base) {
                ++low;
            }
            arr[high] = arr[low];
            System.out.println(JSON.toJSONString(arr));
        }
        arr[low] = base;
        System.out.println(JSON.toJSONString(arr));
        return low;
    }

}
