package syslog.udp;

import org.apache.poi.ss.formula.functions.T;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SyslogUdpTest
 */
public class SyslogUdpTest {

    static class UdpDemoThread implements Runnable {
        public static boolean start = true;
        public static DatagramSocket ds = null;

        @Override
        public void run() {
            startUdpServer();
        }

        public static void startUdpServer() {
            int count = 0;
            AtomicInteger in = new AtomicInteger();
            DatagramPacket dp = new DatagramPacket(new byte[2 * 1024], 2 * 1024);

            try {
                ds = new DatagramSocket(514);
//                ds.setSoTimeout(3000);
                ds.setReceiveBufferSize(640000);
//            ds.setReceiveBufferSize(1280000);
                while (start) {
                    try {
                        ds.receive(dp);
                        count++;
                        System.out.println(in.incrementAndGet() + " ~ " + dp.getLength() + "[" + count + "]");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UdpDemoThread udpDemoThread = new UdpDemoThread();
        Thread thread = new Thread(udpDemoThread);
        thread.start();
        Thread.sleep(5000);
        udpDemoThread.start = false;
        udpDemoThread.ds.close();
         System.out.println("interrupt");
        Thread.sleep(1000000000);
    }
}
