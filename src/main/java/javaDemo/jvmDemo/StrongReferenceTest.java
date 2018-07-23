package javaDemo.jvmDemo;
/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at jvm.StrongReferenceTest$BiggerObject.<init>(StrongReferenceTest.java:10)
	at jvm.StrongReferenceTest.main(StrongReferenceTest.java:18)
 * @author xlj
 *
 */
public class StrongReferenceTest {
	private static class BiggerObject {// 占用空间的一个大对象
		public int[] values;
		public String name;

		public BiggerObject(String name) {
			this.name = name;
			values = new int[1024];
		}
	}

	public static void main(String[] args) {
		int count = 1000000;// 对象的个数，保证使得堆内存溢出
		BiggerObject[] values = new BiggerObject[count];
		for (int i = 0; i < count; i++) {
			values[i] = new BiggerObject("Object-" + i);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(values[i].name);
		}
	}
}