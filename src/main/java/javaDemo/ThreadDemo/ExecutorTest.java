package javaDemo.ThreadDemo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 线程池newFixedThreadPool的使用。 守护线程池
 */
public class ExecutorTest {
	public static void main(String args[]) throws InterruptedException {

		final ExecutorService executor = Executors.newFixedThreadPool(5);

		// Thread executorThread = new Thread(new ExecutorThread());
		// executorThread.setDaemon(true);
		// executor.execute(executorThread);
		ExecutorService exec = Executors.newFixedThreadPool(4, new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread t = Executors.defaultThreadFactory().newThread(r);
				t.setDaemon(true);
				return t;
			}
		});
		for (int i = 0; i < 10; i++) {

			exec.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("a");
				}
			});
		}
		Thread.sleep(5000);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				executor.shutdownNow();
				System.out.println("JVM Exit!");
			}
		});
	}
}
