package javaDemo.ThreadDemo;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerTread loggerTread;
    private final PrintWriter writer;
    private boolean isShutdown;
    private int reservations;

    public LogService(BlockingQueue<String> queue, LoggerTread loggerTread, PrintWriter writer, boolean isShutdown) {
        this.queue = queue;
        this.loggerTread = loggerTread;
        this.writer = writer;
        this.isShutdown = isShutdown;
    }

    public void stest() {
        loggerTread.start();
    }

    public void stop() {
        synchronized (this) {
            loggerTread.interrupt();
        }
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalArgumentException();
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerTread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (LogService.this) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                    }
                    String msg = queue.take();
                    synchronized (LogService.this) {
                        --reservations;
                    }
                    writer.println(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
