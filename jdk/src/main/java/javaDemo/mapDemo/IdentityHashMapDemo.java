package javaDemo.mapDemo;

import java.util.HashMap;
import java.util.IdentityHashMap;

public class IdentityHashMapDemo {

	public static void main(String[] args) {
		Integer a = new Integer(123456);
		Integer b = new Integer(123456);
		System.out.println(a.equals(b));
		HashMap hashMap = new HashMap();
		IdentityHashMap identityHashMap = new IdentityHashMap();
		hashMap.put(a, 1);
		hashMap.put(b, 2);
		identityHashMap.put(a, 1);
		identityHashMap.put(b, 2);
		System.out.println(hashMap);
		System.out.println(identityHashMap);
		// {123456=2}
		// {123456=2, 123456=1}

	}
}
