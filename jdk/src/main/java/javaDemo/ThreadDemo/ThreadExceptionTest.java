package javaDemo.ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadExceptionTest {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		Future<Boolean> future = executor.submit(new CallableTask());
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executor.shutdown();// 必须不能忘，否则会一致卡在那里
	}
}

class CallableTask implements Callable<Boolean> {
	public Boolean call() throws Exception {
		// InputStream in = new FileInputStream(new File("xx.pdf"));
		int num = 3 / 10;
		return false;
	}

}