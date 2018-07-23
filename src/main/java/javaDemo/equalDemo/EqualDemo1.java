package javaDemo.equalDemo;

public class EqualDemo1 {

	public static void main(String[] args) {
		Person p1 = new Person("巡礼", 100);
		Person p2 = new Person("巡礼", 100);
		System.out.println(p1.hashCode() + ":" + p2.hashCode() + ":" + p1.equals(p2));
		System.out.println("a".hashCode() + ":" + new String("a").hashCode());
	}
}

class Person {
	String name;
	Integer age;

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}*/
}