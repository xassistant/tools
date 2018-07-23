package javaDemo.junit4;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

/*
 * http://blog.csdn.net/knighttools/article/details/44630975
*/
public class ClassUnderTest {
	public boolean callArgumentInstance(File file) {

		return file.exists();

	}

	@Test
	public void testCallArgumentInstance() {
		File file = PowerMockito.mock(File.class);
		ClassUnderTest underTest = new ClassUnderTest();
		PowerMockito.when(file.exists()).thenReturn(true);
		Assert.assertTrue(underTest.callArgumentInstance(file));
	}
}
