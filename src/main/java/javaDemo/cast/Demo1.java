package javaDemo.cast;

/**
 * boolean 1bit; byte 1byte; char 2byte; short 2byte;int 4byte ; float 4byte ;
 * long 8byte ; double 8byte
 * 
 * @author admin
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		byte b = (byte) -1;
		int i = b;
		System.out.println(i);

		char c = (char) -1;
		int j = c;
		System.out.println(j);

		Employee e = new Manager();
		System.out.println(e.i);
	}
}

class Employee {
	int i = 0;
}

class Manager extends Employee {
	String s = "a";
}
