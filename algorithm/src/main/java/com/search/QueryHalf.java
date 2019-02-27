package com.search;

/**
 * @Date 2019/2/19 17:33
 * 二分法查找
 */
public class QueryHalf {
    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int bsearch = bsearch(arr, "g");
        System.out.println(bsearch);
        System.out.println(recursionSearch(arr, "g", 0, arr.length - 1));
    }

    /**
     * 递归查询
     *
     * @param arr
     * @param str
     * @param left
     * @param right
     * @return
     */
    private static int recursionSearch(String[] arr, String str, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid].compareTo(str) > 0) {
                return recursionSearch(arr, str, left, mid - 1);
            } else if (arr[mid].compareTo(str) < 0) {
                return recursionSearch(arr, str, mid + 1, right);
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 非递归
     *
     * @param arr
     * @param str
     * @return
     */
    private static int bsearch(String[] arr, String str) {
        int left = 0, right = arr.length - 1, mid = 0;
        while (left < right) {
            mid = (left + right) >> 1;
            String midData = arr[mid];
            if (midData.compareTo(str) > 0) {
                right = mid;
            } else if (midData.compareTo(str) < 0) {
                left = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }

}
