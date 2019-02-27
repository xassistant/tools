package com.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2019/2/20 16:30
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        System.out.println(map);

    }
}
