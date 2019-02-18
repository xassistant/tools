package com.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 归并排序
 */
@SuppressWarnings("ALL")
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
    }

    private static void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int middle = (left + right) >> 1;
            sort(arr, left, middle, tmp);
            sort(arr, middle + 1, right, tmp);
            merge(arr, left, middle, right, tmp);
        }
    }

    private static void merge(int[] array, int lo, int mid, int hi, int[] temp) {
        int left = lo;
        int right = mid + 1;
        int k = lo;
        while (left <= mid && right <= hi) {
            if (array[left] < array[right]) {
                temp[k++] = array[left++];
            } else {
                temp[k++] = array[right++];
            }
        }
        //处理剩余未合并的部分
        while (left <= mid) {
            temp[k++] = array[left++];
        }
        while (right <= hi) {
            temp[k++] = array[right++];
        }
        //将临时数组中的内容存储到原数组中
        while (lo <= hi) {
            array[lo] = temp[lo++];
        }
    }
}