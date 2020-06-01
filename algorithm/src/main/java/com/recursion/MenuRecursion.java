package com.recursion;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MenuRecursion {
    static class Menu {
        Integer id;
        String name;
        Integer pId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getpId() {
            return pId;
        }

        public void setpId(Integer pId) {
            this.pId = pId;
        }
    }

    public static void main(String[] args) {
        List<Menu> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Menu menu = new Menu();
            menu.setId(i);
            menu.setpId(null);
            menu.setName("菜单：" + i);
            list.add(menu);
        }
        for (int i = 10; i < 50; i = i + 3) {
            int pId = i / 5;
            for (int n = 0; n < list.size(); n++) {
                if (list.get(n).getId() == pId) {
                    Menu menu = new Menu();
                    menu.setId(i);
                    menu.setName("子菜单：" + i);
                    menu.setpId(pId);
                    list.add(menu);
                }
            }
        }
        System.out.println(JSON.toJSONString(list));


    }


}
