package javaDemo.enumDemo;

public enum ColorDemo2 {

	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

	private String name;
	private int index;

	private ColorDemo2(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static void main(String[] args) {

		// 输出某一枚举的值
		System.out.println(ColorDemo2.RED.getName());
		System.out.println(ColorDemo2.RED.getIndex());

		// 遍历所有的枚举
		for (ColorDemo2 color : ColorDemo2.values()) {
			System.out.println(color + "  name: " + color.getName() + "  index: " + color.getIndex());
		}
	}
}