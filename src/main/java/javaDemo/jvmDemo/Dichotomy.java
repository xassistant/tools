package javaDemo.jvmDemo;

public class Dichotomy {

	public static void main(String[] args) {
		int[] is = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		long start = System.currentTimeMillis();
		System.out.println("順序： -->"+ Linear_Search(is, 4));
 		System.out.println(System.currentTimeMillis()-start);
		
 		start = System.currentTimeMillis();
 		System.out.println("循环： -->"+ binarySearch(is, 4));
 		System.out.println(System.currentTimeMillis()-start);
 		
 		start = System.currentTimeMillis();
		System.out.println("迭代：" + binarySearch(is, 4, 0, is.length - 1));
		System.out.println(System.currentTimeMillis()-start);
	}

	/**
	 * 普通顺序查找
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static int Linear_Search(int[] data, int key) {
		if (data == null || data.length <= 0) {
			return -1;
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 二分法：顺序、循环
	 * 
	 * @param data
	 * @param i
	 * @return
	 */
	private static int binarySearch(int[] data, int i) {
		if (data == null || data.length <= 0) {
			return -1;
		}
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == i) {
				return mid;
			} else if (data[mid] < i) {
				low = mid + 1;
			} else if (data[mid] > i) {
				high = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * 二分法：迭代查找
	 * 
	 * @param data
	 * @param i
	 * @param low
	 * @param high
	 * @return
	 */
	private static int binarySearch(int[] data, int i, int low, int high) {
		if (data == null || data.length <= 0) {
			return -1;
		}
		if (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == i) {
				return i;
			} else if (data[mid] < i) {
				binarySearch(data, i, mid + 1, high);
			} else if (data[mid] > i) {
				binarySearch(data, i, low, mid - 1);
			}

		}
		return i;
	}

}
