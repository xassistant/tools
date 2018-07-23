package javaDemo.cloneDemo;

import java.util.Date;

import org.junit.Test;

public class CloneDemo1 {
	@Test
	public void test() throws CloneNotSupportedException {
		Employee e1 = new Employee("老王", 48);
		Laowang(e1);
		System.out.println(e1.getAge());
		String[] s1 = new String[] { "1" };
		SHUzu(s1);
		System.out.println(s1[0]);
		/**************************/
		Employee e2 = (Employee) e1.clone();
		e2.setName("老马");
		System.out.println(e1.getName());
	}

	private String[] SHUzu(String[] s1) {

		s1[0] = "b";
		return s1;
	}

	private void Laowang(Employee e1) {
		e1.setAge(58);
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
		Employee cloned = (Employee) super.clone();// 浅克隆，如果有类似Data的新的类，也是要克隆的，如下边的Employee1
		return cloned;
	}

}

class Employee1 implements Cloneable {
	public String name = "";
	private Date hireDay = null;

	public Object clone() throws CloneNotSupportedException {
		Employee1 cloned = (Employee1) super.clone(); // 浅克隆
		cloned.hireDay = (Date) hireDay.clone(); // 克隆子对象
		return cloned;
	}
}