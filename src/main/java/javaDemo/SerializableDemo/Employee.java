package javaDemo.SerializableDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = -6326841297028576695L;
	private String name;

	Employee(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("D:/version.txt")));
		out.writeObject(new Manager("aaa", 123));

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("D:/version.txt")));
		Manager ma = (Manager) in.readObject();
		
		long serialVersionUID = ObjectStreamClass.lookup(Class.forName("javaPackage.SerializableDemo.Manager"))
				.getSerialVersionUID();
		System.out.println(serialVersionUID);
		// -8954526937313564748

		// -7239575809535007161

		// -8880583025650372198

	}
}

class Manager extends Employee implements Serializable {

	private static final long serialVersionUID = -8880583025650372198L;
	private int id;
	private int ages;

	Manager(String n, int id) {
		super(n);
		this.id = id;
	}

}
