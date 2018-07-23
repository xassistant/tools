package javaDemo.resourceDemo;

import javaDemo.floatDemo.FloatDemo1;

public class ResouceDemo {
	public static void main(String[] args) {
		System.out.println(FloatDemo1.class.getResource(""));
		System.out.println(FloatDemo1.class.getResource("/"));
		System.out.println(ClassLoader.class.getResource("/"));
	}
}
