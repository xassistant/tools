package com.alibaba.datax.core.util;

import com.alibaba.datax.common.exception.DataXException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;

public class HttpClientUtilTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtilTest.class);

	@Before
	public void before() {
		System.setProperty("datax.home", "F:\\github\\tools\\target\\test-classes");
	}

	@Test
	public void testExecuteAndGet() throws Exception {
		HttpGet httpGet = HttpClientUtil.getGetRequest();
		httpGet.setURI(new URI("http://127.0.0.1:8080/raspwaf/sqlInt.jsp?username=1"));
		try {
			HttpClientUtil client = HttpClientUtil.getHttpClientUtil();
			String executeAndGet = client.executeAndGet(httpGet);
			System.out.println(String.format("第%s次", executeAndGet));
		} catch (Exception e) {
			LOGGER.info("msg:" + e.getMessage(), e);
			Assert.assertNotNull(e);
			Assert.assertEquals("Response Status Code : 400", e.getMessage());

		}

	}

	@Test
	public void testExecuteAndGetWithRetry() throws Exception {
		String url = "http://127.0.0.1:8080/raspwaf/sqlInt.jsp?username=1";
		HttpRequestBase httpRequestBase = new HttpGet(url);

		HttpClientUtil httpClientUtil = HttpClientUtil.getHttpClientUtil();
		String str = httpClientUtil.executeAndGetWithRetry(httpRequestBase, 5, 1000l);
		System.out.println(str);
//		Assert.assertEquals(str, "成功");

//		try {
//			httpClientUtil.executeAndGetWithRetry(httpRequestBase, 2, 1000l);
//		} catch (Exception e) {
//			Assert.assertTrue(e instanceof DataXException);
//		}
		httpClientUtil.destroy();
	}

	/**
	 * 和测试方法一样：testExecuteAndGetWithRetry()，只是换了一种写法，直接采用 Mockito 进行验证的。
	 */
	@Test
	public void testExecuteAndGetWithRetry2() throws Exception {
		String url = "http://127.0.0.1/:8080";
		HttpRequestBase httpRequestBase = new HttpGet(url);

		HttpClientUtil httpClientUtil = Mockito.spy(HttpClientUtil.getHttpClientUtil());

		Mockito.doThrow(new Exception("one")).doThrow(new Exception("two")).doThrow(new Exception("three"))
				.doReturn("成功").when(httpClientUtil).executeAndGet(httpRequestBase);

		String str = httpClientUtil.executeAndGetWithRetry(httpRequestBase, 4, 1000l);
		Assert.assertEquals(str, "成功");

		Mockito.reset(httpClientUtil);

		Mockito.doThrow(new Exception("one")).doThrow(new Exception("two")).doThrow(new Exception("three"))
				.doReturn("成功").when(httpClientUtil).executeAndGet(httpRequestBase);
		try {
			httpClientUtil.executeAndGetWithRetry(httpRequestBase, 2, 1000l);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DataXException);
			Assert.assertTrue(e.getMessage().contains("two"));
		}
		httpClientUtil.destroy();
	}

	// 单独运行可以成功
	// private String url = "http://aBadAddress:8080/";
	//
	// @Rule
	// public ExpectedException expectedException = ExpectedException.none();
	//
	// @Test
	// public void testExecuteAndGetWithRetry_exception() throws Exception {
	// HttpRequestBase httpRequestBase = new HttpGet(url);
	//
	// HttpClientUtil httpClientUtil = HttpClientUtil.getHttpClientUtil();
	//
	// expectedException.expect(UnknownHostException.class);
	// httpClientUtil.executeAndGetWithRetry(httpRequestBase, 3, 1000L);
	// }

}
