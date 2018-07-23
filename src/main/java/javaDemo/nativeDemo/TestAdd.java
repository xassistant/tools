package javaDemo.nativeDemo;

//fileName: TestAdd
//System.loadLibrary("NativeAdd"); 加载了动态类库，在Windows下加载的就是NativeAdd.dll，在Linux中加载的就是libNativeAdd.so
public class TestAdd {
	public static void main(String[] args) {
		System.loadLibrary("NativeAdd");// 加载由C编译器生成的DLL文件。

		NativeAdd na = new NativeAdd();
		System.out.println("3 + 4 = " + na.add(3, 4));
	}
}

class NativeAdd {
	public native int add(int x, int y);
}
