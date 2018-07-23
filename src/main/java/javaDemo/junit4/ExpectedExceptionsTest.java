package javaDemo.junit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionsTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void verifiesTypeAndMessage() {
		// 检测是否抛出了正确的异常信息
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Runtime exception occurred");
		throw new RuntimeException("Runtime exception occurred");
	}
}