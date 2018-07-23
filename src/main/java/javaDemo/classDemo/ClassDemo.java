package javaDemo.classDemo;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.ProtectionDomain;

public class ClassDemo {

	public static void main(String[] args) {

		try {
			Class cls = Class.forName("javaPackage.ClassDemo");

			// returns the name of the class
			System.out.println("Class = " + cls.getName());

			// returns the ProtectionDomain of this class.
			ProtectionDomain p = cls.getProtectionDomain();
			System.out.println(p);
			URL localUrl = ClassDemo.class.getProtectionDomain().getCodeSource().getLocation();
			String path = null;
			try {
				path = URLDecoder.decode(localUrl.getFile().replace("+", "%2B"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.toString());
		}
	}
}