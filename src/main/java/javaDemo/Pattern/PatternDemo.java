package javaDemo.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * 1. 正则表达式中以’()’标记的子表达式所匹配的内容就是一个分组(group). 2. 类似于(?:pattern)格式的子表达式不能算是一个分组
 * 
 * @author admin
 *
 */
public class PatternDemo {

	@Test
	public void test1() {
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "(John)";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("groupCount is -->" + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(0));
		}
		while (matcher.matches()) {
			System.out.println("found: " + matcher.group(0));
		}
	}

	@Test
	public void test2() {
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "John";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("groupCount is -->" + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
		}
	}

	@Test
	public void test3() {
		// 类似于(?:pattern)格式的子表达式不能算是一个分组.
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "(?:John)";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("groupCount is -->" + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
		}
	}

	@Test
	public void test4() {
		/**
		 * 组零始终代表整个表达式。 之所以这样命名捕获组是因为在匹配中，保存了与这些组匹配的输入序列的每个子序列。捕获的子序列稍后可以通过 Back
		 * 引用在表达式中使用，也可以在匹配操作完成后从匹配器获取。 以 (?) 开头的组是纯的非捕获 组，它不捕获文本，也不针对组合计进行计数。
		 * 
		 */
		String regex = "(x)(y\\w*)(z)";

		String input = "exy123z,aaaxy456z";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);
		System.out.println(m.groupCount());
		while (m.find()) {
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
	}

	/**
	 * matches:整个匹配，只有整个字符序列完全匹配成功，才返回True，否则返回False。但如果前部分匹配成功，将移动下次匹配的位置。
	 * lookingAt:部分匹配，总是从第一个字符进行匹配,匹配成功了不再继续匹配，匹配失败了,也不继续匹配。
	 * find:部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置。
	 * reset:给当前的Matcher对象配上个新的目标，目标是就该方法的参数；如果不给参数，reset会把Matcher设到当前字符串的开始处。
	 * 使用示例代码来展示他们的区别更清晰明了：
	 */
	@Test
	public void test5() {
		Pattern pattern = Pattern.compile("\\d{3,5}");
		String charSequence = "123-34345-234-00";
		Matcher matcher = pattern.matcher(charSequence);

		// 虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
		System.out.println(matcher.matches());
		// 测试匹配位置
		matcher.find();
		System.out.println(matcher.start());

		// 使用reset方法重置匹配位置
		matcher.reset();

		// 第一次find匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.find());
		System.out.println(matcher.group() + " - " + matcher.start());
		// 第二次find匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.find());
		System.out.println(matcher.group() + " - " + matcher.start());

		// 第一次lookingAt匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.group() + " - " + matcher.start());

		// 第二次lookingAt匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.group() + " - " + matcher.start());
	}

}
