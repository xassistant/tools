package javaDemo.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayDemo1 {
	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		strList.add("aa");
		strList.add("bb");
		System.out.println(strList.toArray());

		String[] str = new String[] { "s", "a" };
		Collections.addAll(strList, str);
		Iterator<String> iterator = strList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		// 或者
		List<String> asList = Arrays.asList(str);
		System.out.println(asList);
	}
}
