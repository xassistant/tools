package com;

import com.collection.ConcurrentHashMap7;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date 2019/2/20 12:26
 */
public class Test extends ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList();
        list.add(null);
        list.add(null);
        list.add(1);
        list.add(1);
        System.out.println(list);

        Set set = new HashSet();
        set.add(null);
        set.add(null);
        set.add(1);
        set.add(1);
        System.out.println(set);

        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(null, null);
        hashMap.put(null, null);
        hashMap.put(1, 1);
        hashMap.put(1, 1);
        for (int i = 0; i < 100; i++) {
            hashMap.put(i, i);
        }
        hashMap.remove(1);
        System.out.println(hashMap);
        // fail fast 不能修改数据
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
        }

        Hashtable hashtable = new Hashtable();
//        hashtable.put(null,1);// NullPointException
        hashtable.put(1, 1);
        hashtable.put(1, 1);
        hashtable.put(2, 1);
        System.out.println(hashtable);

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            concurrentHashMap.put(i, i);
        }
        for (Map.Entry<Object, Object> entry : concurrentHashMap.entrySet()) {
            concurrentHashMap.put("aa", "fd");
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        concurrentHashMap.remove(1);
        MapTest mapTest = new MapTest("xiaoming");
        MapTest1 mapTest1 = new MapTest1(mapTest);
        mapTest.setName("wangxiaohua");
        System.out.println(mapTest1.getMapTest().getName());
        /////////////////////////////////
        int a = 0;
        while (true) {
            //a只要不等4，线程就让出CPU，等待调度器再次执行此线程
            Thread.sleep(1000);
            Thread.yield(); //让出CPU，线程进入就绪态
            System.out.println("after yield run");
            break;
        }
        /////////////////
        new Thread(() -> {
            System.out.println(Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().isInterrupted());
            Thread.interrupted();// 还原操作
            System.out.println(Thread.currentThread().isInterrupted());
        }).start();

        //////////////////
        ConcurrentHashMap7<Object, Object> map7 = new ConcurrentHashMap7();
        for (int i = 0; i < 10; i++) {
            map7.put(i, i);
            HashEntity hashEntity = new HashEntity();
            hashEntity.setName("aa"+i);
            map7.put(hashEntity, i);
        }
        for (Map.Entry<Object, Object> entry : map7.entrySet()) {
            map7.put("aa", "fd");
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        int size = map7.size();
        map7.remove(1);
        map7.get(1);
        System.out.println(size);
        //////////////////

        AtomicInteger integer = new AtomicInteger();
        int i = integer.incrementAndGet();

        ////////////////
        Test test = new Test();
        while (true){
            test.lock();
            System.out.println("lock sucess");
            break;
        }
        System.out.println("lock end");
    }

    static class MapTest1 {
        private MapTest mapTest;

        public MapTest1(MapTest mapTest) {
            this.mapTest = mapTest;
        }

        public MapTest getMapTest() {
            return mapTest;
        }

        public void setMapTest(MapTest mapTest) {
            this.mapTest = mapTest;
        }
    }

    static class MapTest {
        String name;

        public MapTest(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    static class HashEntity {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "HashEntity{" + "name='" + name + '\'' + '}';
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 4;
        }
    }
}
