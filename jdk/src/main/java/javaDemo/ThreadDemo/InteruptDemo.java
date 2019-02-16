package javaDemo.ThreadDemo;

public class InteruptDemo {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("a");
				}
			}
		});
		thread.start();
		while (true)
			thread.interrupt();
	}
}
