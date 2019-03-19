package javaDemo.cloneDemo;

import java.util.Date;

import cn.itcast.heima2.User;
import org.junit.Test;

public class CloneDemo1 {
    @Test
    public void test() throws CloneNotSupportedException, InterruptedException {
//        Employee e1 = new Employee("老王", 48);
//        e1.setAge(100);
//        Employee clone1 = (Employee) e1.clone();
//        System.out.println(clone1.getAge());

        User user = new User("xiaomi", 100);
        User user1 = new User("asdf", 11);

        Employee1 employee1 = new Employee1("老王", user);
        Employee1 employee11 = (Employee1) employee1.clone();
//        employee11.setUser(user1);
        employee11.getUser().setName("aaaaaaaaaaa");
        System.out.println(employee1.getUser().getName()+":"+employee11.getUser().getName());
    }


}

class Employee implements Cloneable {
    String name;
    Integer age;

    public Employee(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        return cloned;
    }

}

class Employee1 implements Cloneable {
    public String name;
    private User user;

    public Employee1(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Object clone() throws CloneNotSupportedException {
        Employee1 cloned = (Employee1) super.clone();
        cloned.user = (User) user.clone();
        return cloned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}