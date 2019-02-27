package com.collection;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Date 2019/2/20 17:25
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
            HashEntity hashEntity = new HashEntity();
            hashEntity.setName("aa"+i);
            map.put(hashEntity, i);
        }
        System.out.println(map);
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
