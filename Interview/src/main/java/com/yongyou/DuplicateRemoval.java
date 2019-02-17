package com.yongyou;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date 2019/2/16 14:16
 * 找到重复的数据，也可以去重复
 */
public class DuplicateRemoval {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(1);
        set1.add(3);
        set1.add(2);

        set2.add(5);
        set2.add(1);

        Set<Integer> result = new HashSet<>();
        result.addAll(set1);

        // 找到重复数据
        result.retainAll(set2);
        System.out.println(result);
        // 找到重复数据
//        set1.retainAll(set2);
//        System.out.println(set1);
        // 去重复
        set1.addAll(set2);
        System.out.println(set1);
    }
}
