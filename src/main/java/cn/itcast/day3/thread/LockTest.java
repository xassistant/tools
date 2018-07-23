package cn.itcast.day3.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final Business business = new Business();
//		ExecutorService service = Executors.newFixedThreadPool(3);
		ExecutorService service = Executors.newFixedThreadPool(4, new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread t = Executors.defaultThreadFactory().newThread(r);
				t.setDaemon(true);
				return t;
			}
		});
		for (int i = 0; i < 30; i++) {
			service.execute(new Runnable() {
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					business.service();
					System.out.println("a");
				}
			});
		}
		Thread.sleep(10000000);
		service.shutdown();
	}

	 

}

class Business {
	private int count = 0;
	Lock lock = new ReentrantLock();

	public void service() {
		lock.lock();
		count++;
		try {
			Thread.sleep(10);
			System.out.println(count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
