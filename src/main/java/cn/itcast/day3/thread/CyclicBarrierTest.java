package cn.itcast.day3.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
//						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("" + Thread.currentThread().getName() + "aaaa"
								+ cb.getNumberWaiting() + "as");
						cb.await();

//						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("" + Thread.currentThread().getName() + "bbbb"
								+ cb.getNumberWaiting() + "bs");
						cb.await();
//						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("" + Thread.currentThread().getName() + "cccc"
								+ cb.getNumberWaiting() + "cs");
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);

		}
		service.shutdown();
	}

}
