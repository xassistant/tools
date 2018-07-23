package javaDemo.cloneDemo.test;

import org.junit.Test;

import javaDemo.cloneDemo.Animal;

class Rat extends Animal {

}

public class Cat extends Animal {
	/**
	 * 测试时候把cat类放到其他报下，那么证明了，protected只能被同一级包下调用
	 */
	@Test
	public void test() {
		this.crowl();
	}

	public void crowl() {
		crowl("zhi zhi"); // 没有问题，继承了Animal中的protected方法——crowl(String)
		Rat r = new Rat();
//		r.crowl("miao miao"); // wrong, The method crowl(String) from the type Animal is not visible
	}
}