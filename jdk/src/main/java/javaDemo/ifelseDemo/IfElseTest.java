package javaDemo.ifelseDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("unchecked")
public class IfElseTest {
	static Map<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>();
	static {
		for (int i = 20; i < 100; i += 5) {
			final Integer j = i;
			map.put(i, new HashMap<Integer, Integer>() {
				{
					put(j, j + 5);
				}
			});
		}
	}

	public static void main(String[] args) {
		IfElseTest t = new IfElseTest();
		Integer highMax = 36;
		Integer high = test(highMax);
		System.out.println(high);
	}

	private static Integer test(Integer highMax) {
		for (Entry<Integer, HashMap<Integer, Integer>> map_ : map.entrySet()) {
			for (Entry<Integer, Integer> entry : map_.getValue().entrySet()) {
//				System.out.println(map_.getKey() + ":" + entry.getKey() + ":" + entry.getValue());
				return judgeMethod(highMax, map_, entry);
			}

		}
		return null;
	}

	private static Integer judgeMethod(Integer highMax, Entry<Integer, HashMap<Integer, Integer>> map_, Entry<Integer, Integer> entry) {
		if (highMax > entry.getKey() && highMax < entry.getValue()) {
			return map_.getKey();
		}
		return null;
	}
}
