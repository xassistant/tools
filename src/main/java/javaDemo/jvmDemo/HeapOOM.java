package javaDemo.jvmDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+PrintGCDetails
 * 
 * @author xlj 堆内存溢出
 */
public class HeapOOM {

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}
}

class OOMObject {
}