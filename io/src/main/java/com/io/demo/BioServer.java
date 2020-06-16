package com.io.demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class BioServer {

    private String ip;

    private int port;

    public BioServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startListen() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(ip, port));

        while (true) {
            // 阻塞
            Socket socket = serverSocket.accept();
            new RequestHandler(socket).start();
        }
    }

    private class RequestHandler extends Thread {
        private final Socket socket;


        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream in = null;
            InputStreamReader reader = null;
            BufferedReader br = null;
            OutputStream out = null;
            OutputStreamWriter writer = null;
            BufferedWriter bw = null;
            try {
                //输入流用于接收客户端传输过来的数据
                in = socket.getInputStream();
                reader = new InputStreamReader(in);
                br = new BufferedReader(reader);
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    //按照HTTP协议，请求头和请求体之间为一空行分隔
                    if ("".equals(line)) {
                        break;
                    }
                }
                //输出流用于向客户端发送响应消息，需遵从HTTP协议格式
                out = socket.getOutputStream();
                writer = new OutputStreamWriter(out);
                bw = new BufferedWriter(writer);
                bw.write(getResponseText());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //必须在请求读取和响应写入都处理完毕之后才可以调用close方法，将输入流关闭也会导致输出流不可用
                try {
                    Objects.requireNonNull(bw).close();
                    writer.close();
                    out.close();
                    br.close();
                    reader.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String getResponseText() {
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\n");
        sb.append("Content-Type: text/html; charset=UTF-8\n");
        sb.append("\n");
        sb.append("<html>");
        sb.append("  <head>");
        sb.append("    <title>");
        sb.append("      NIO Http Server");
        sb.append("    </title>");
        sb.append("  </head>");
        sb.append("  <body>");
        sb.append("    <h1>Hello World!</h1>");
        sb.append("  </body>");
        sb.append("</html>");

        return sb.toString();
    }

    public static void main(String[] args) {
        BioServer server = new BioServer("127.0.0.1", 8081);
        try {
            server.startListen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}