package javaDemo.ThreadDemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class NonInterrupptableSocketThreadTest {

    public static class ReaderThread extends Thread {
        private static final int BUFSZ = 1024;
        private final Socket socket;
        private final InputStream in;

        public ReaderThread(Socket socket) throws IOException {
            this.socket = socket;
            this.in = socket.getInputStream();
        }

        @Override
        public void interrupt() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                super.interrupt();
            }
        }

        @Override
        public void run() {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                try {
                    int count = in.read(buf);
                    if (count < 0) {
                        break;
                    } else if (count > 0) {
                        processBuffer(buf, count);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void processBuffer(byte[] buf, int count) {
            byte[] arrBuf = new byte[count];
            System.arraycopy(buf, 0, arrBuf, 0, count);
            System.out.println(new String(arrBuf));
        }
    }
}
