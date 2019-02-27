package com.internet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Date 2019/2/18 9:25
 */
public class LRU extends LinkedHashMap {

    private final int capcity;

    public LRU(int capcity) {
        super((int) Math.ceil(capcity / 0.75), 0.75f, true);
        this.capcity = capcity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return capcity < size();
    }

    public static void main(String[] args) {
        LRU lru = new LRU(5);
        lru.put(1,1);
        lru.put(2,2);
        lru.put(3,3);
        lru.put(5,5);
        lru.put(4,4);
        lru.put(16,16);
        System.out.println(lru);
        lru.get(3);
        System.out.println("=====================");
        System.out.println(lru);
    }
}
