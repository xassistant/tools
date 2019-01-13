package javaDemo.ThreadDemo;

/**
 * 线程应该由拥有者来停止，如new  或者 线程池
 */
public class ThreadTransitivityTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        Thread.sleep(5000);
        test((thread));
    }

    public static void test(Thread thread) {
        thread.interrupt();
    }
}

class ThreadTest implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}