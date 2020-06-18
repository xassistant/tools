package com.sort;

public class UserInfo implements Comparable<UserInfo> {

    private int id;

    private String name;

    private int age;

    public UserInfo(int id, String name, int age) {

        this.id = id;

        this.name = name;

        this.age = age;

    }

    @Override

    public int compareTo(UserInfo o) {

        return age > o.age ? 1 : (age == o.age ? 0 : -1);

    }

    @Override

    public String toString() {

        return "UserInfo[id=" + id + " name=" + name + " age=" + age + "]";

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public int getAge() {

        return age;

    }

    public void setAge(int age) {

        this.age = age;

    }

}