package cn.itcast.day3.thread;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService service =  Executors.newScheduledThreadPool(3);
		service.scheduleAtFixedRate(
				new Runnable(){
					public void run() {
						System.out.println(Thread.currentThread().getName());
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				},
				10,
				1,
				TimeUnit.SECONDS);
		
//		while(true){
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName());
//		}
	}

}
