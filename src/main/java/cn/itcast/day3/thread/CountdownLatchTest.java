package cn.itcast.day3.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("" + Thread.currentThread().getName() + "aaa");
						cdOrder.await();
						System.out.println("" + Thread.currentThread().getName() + "bbb");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("" + Thread.currentThread().getName() + "ccc");
						cdAnswer.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		try {
			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("" + Thread.currentThread().getName() + "ddd");
			cdOrder.countDown();
			System.out.println("" + Thread.currentThread().getName() + "eee");
			cdAnswer.await();
			System.out.println("" + Thread.currentThread().getName() + "fff");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();

	}
}
