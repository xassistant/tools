package javaDemo.inner;

import java.util.Date;

/**
 * 静态内部类static inner class (also called nested class)
 * 
 * 成员内部类member inner class
 * 
 * 局部内部类local inner class
 * 
 * 匿名内部类anonymous inner class
 * 
 * @author admin 作为public 类应该和文件名一致 源文件中公关类名要与文件名相同
 */
public class InnerClass {

}

/**
 * 
 * 静态内部类
 * 
 */
class StaticInner {
	private static int a = 4;

	// 静态内部类
	public static class Inner {
		public void test() {
			// 静态内部类可以访问外部类的静态成员
			// 并且它只能访问静态的
			System.out.println(a);
		}

	}
}

class StaticInnerClassTest {

	public static void main(String[] args) {
		StaticInner.Inner inner = new StaticInner.Inner();
		inner.test();
	}
}

/***
 * 非静态成员内部类
 * 
 * @author admin
 * 
 */
class MemberInner {
	private int d = 1;
	private int a = 2;

	// 定义一个成员内部类
	public class Inner2 {
		private int a = 8;

		public void doSomething() {
			// 直接访问外部类对象
			System.out.println(d);
			System.out.println(a);// 直接访问a，则访问的是内部类里的a

			// 如何访问到外部类里的a呢？
			System.out.println(MemberInner.this.a);
		}

	}

}

class MemberInnerClassTest {

	public static void main(String[] args) {

		// 创建成员内部类的对象
		// 需要先创建外部类的实例
		MemberInner.Inner2 inner = new MemberInner().new Inner2();

		inner.doSomething();
	}
}

/**
 * 局部内部类Local Inner Class
 */

class LocalInner {
	int a = 1;

	public void doSomething() {
		int b = 2;
		final int c = 3;
		// 定义一个局部内部类
		class Inner3 {
			public void test() {
				System.out.println("Hello World");
				System.out.println(a);

				// 不可以访问非final的局部变量
				// error: Cannot refer to a non-final variable b inside an inner
				// class defined in a different method
				// System.out.println(b);

				// 可以访问final变量
				System.out.println(c);
			}
		}

		// 创建局部内部类的实例并调用方法
		new Inner3().test();
	}
}

class LocalInnerClassTest {
	public static void main(String[] args) {
		// 创建外部类对象
		LocalInner inner = new LocalInner();
		// 调用外部类的方法
		inner.doSomething();
	}

}

/**
 * 匿名内部类Anonymous Inner Class 匿名内部类就是没有名字的局部内部类，不使用关键字class, extends,
 * implements, 没有构造方法。
 * 
 * 匿名内部类隐式地继承了一个父类或者实现了一个接口。
 * 
 * 匿名内部类使用得比较多，通常是作为一个方法参数。
 */

class AnonymouseInnerClass {

	@SuppressWarnings("deprecation")
	public String getDate(Date date) {
		return date.toLocaleString();

	}

	public static void main(String[] args) {
		AnonymouseInnerClass test = new AnonymouseInnerClass();

		// 打印日期：
		String str = test.getDate(new Date());
		System.out.println(str);
		System.out.println("----------------");

		// 使用匿名内部类
		String str2 = test.getDate(new Date() {
		});// 使用了花括号，但是不填入内容，执行结果和上面的完全一致
			// 生成了一个继承了Date类的子类的对象
		System.out.println(str2);
		System.out.println("----------------");

		// 使用匿名内部类，并且重写父类中的方法
		String str3 = test.getDate(new Date() {

			// 重写父类中的方法
			@Override
			@Deprecated
			public String toLocaleString() {
				return "Hello: " + super.toLocaleString();
			}

		});

		System.out.println(str3);
	}
}