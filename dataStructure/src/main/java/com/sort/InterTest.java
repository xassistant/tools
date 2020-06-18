package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterTest {

    public static void main(String[] args) {

        List<UserInfo> userInfoList = new ArrayList<>();

        userInfoList.add(new UserInfo(0, "大强", 12));

        userInfoList.add(new UserInfo(1, "大黄", 18));

        userInfoList.add(new UserInfo(2, "大黑", 16));

        userInfoList.add(new UserInfo(0, "大白", 52));

        userInfoList.add(new UserInfo(0, "大红", 8));

        userInfoList.add(new UserInfo(0, "大花", 16));

        userInfoList.add(new UserInfo(0, "大菜", 36));

        System.out.println("排序前");

        for (UserInfo userInfo : userInfoList) {

            System.out.println(userInfo);

        }

        Collections.sort(userInfoList);

        System.out.println("排序后");

        for (UserInfo userInfo : userInfoList) {

            System.out.println(userInfo);

        }

    }

}