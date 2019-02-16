package javaDemo.enumDemo;

public enum Color {
	RED, BLUE, BLACK, YELLOW, GREEN,

	DEFINE(000, "red");
	int i;
	String color;

	Color() {

	}

	Color(int i, String color) {
		this.i = i;
		this.color = color;
	}

	public void test() {
		System.out.println("this is a test;");
	}

	public static void main(String[] args) {
		for (Color c : Color.values()) {
			System.out.println(c + ",");
		}
	}
}
