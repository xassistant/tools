package javaDemo.jvmDemo;

import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {
	private static int count = -1;

	public static void main(String[] args) {
		URL[] urls = new URL[1];
		URLClassLoader classLoader = new URLClassLoader(urls);
		testClassLoaser(classLoader);
	}

	private static void testClassLoaser(ClassLoader classLoader) {
		if (count < 0 && classLoader == null) {
			System.out.println("Input object is null");
			return;
		}
		ClassLoader cl = null;
		if (classLoader != null && !(classLoader instanceof ClassLoader)) {
			cl = classLoader.getClass().getClassLoader();
		} else {
			cl = (ClassLoader) classLoader;
		}
		count++;
		String parent = "";
		for (int i = 0; i < count; i++) {
			parent += "Parent ";
		}
		if (cl != null) {
			System.out.println(parent + "ClassLoader name=" + cl.getClass().getName());
			testClassLoaser(cl.getParent());
		} else {
			System.out.println(parent + "ClassLoader name = BootstrapClassLoader");
			count--;
		}
	}

}
