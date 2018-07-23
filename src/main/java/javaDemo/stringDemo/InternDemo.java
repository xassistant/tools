package javaDemo.stringDemo;

import java.util.Random;

/**
 * 说是jdk1.7之前，常量池在方法区中，之后在堆中
 * 
 * @author admin
 *
 */
public class InternDemo {
	static final int MAX = 100000;
	static final String[] arr = new String[MAX];

	public static void main(String[] args) throws Exception {
		String a = "a";
		String param = new String("param" + a);
		String paramSame = param.intern();
		System.out.println(param == paramSame);

		P p = new P("a", 1, "aaa");
		P p1 = new P("a", 1, "bbb");
		System.out.println(p.hashCode() + ":" + p1.hashCode());
		System.out.println(p == p1);
		System.out.println(p.equals(p1));
	}
}

class P {
	String name;
	int age;
	String address;

	public P(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		P other = (P) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}