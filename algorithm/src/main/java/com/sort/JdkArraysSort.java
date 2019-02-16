package com.sort;

import java.util.Arrays;

/**
 *归并排序是稳定排序，它也是一种十分高效的排序，
 * 能利用完全二叉树特性的排序一般性能都不会太差。
 * java中Arrays.sort()采用了一种名为TimSort的排序算法，
 * 就是归并排序的优化版本。
 * 从上文的图中可看出，每次合并操作的平均时间复杂度为O(n)，
 * 而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。
 * 而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。
 */
public class JdkArraysSort {
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        Arrays.sort(arr);
        System.out.println("排序之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
