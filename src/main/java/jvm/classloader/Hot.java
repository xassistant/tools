package jvm.classloader;

public class Hot {
	public void hot() {
		System.out.println(" version 4 : " + this.getClass().getClassLoader());
	}
}