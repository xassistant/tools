package syslog.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main
 */
public class Main {

    public static void startUdpServer() {
        int count = 0;
        AtomicInteger in = new AtomicInteger();
        DatagramPacket dp = new DatagramPacket(new byte[2 * 1024], 2 * 1024);
        DatagramSocket ds;
        try {
            ds = new DatagramSocket(514);
            ds.setSoTimeout(3000);
            ds.setReceiveBufferSize(640000);
//            ds.setReceiveBufferSize(1280000);
            while (true) {
                try {
                    ds.receive(dp);
                    count++;
                    System.out.println(in.incrementAndGet() + " ~ " + dp.getLength() + "[" + count + "]");
                } catch (Exception e) {
                    System.out.println(in.get() + " ~ [" + count + "]");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        startUdpServer();
    }
}
