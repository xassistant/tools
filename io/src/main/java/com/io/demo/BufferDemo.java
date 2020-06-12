package com.io.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {
    public static void main(String[] args) throws IOException {
        // 这里是文件IO处理 test.txt 内容：123456
        FileInputStream fileInputStream = new FileInputStream("/tmp/test.txt");
        // 创建文件的操作管道
        FileChannel channel = fileInputStream.getChannel();
        // NIO面向流，创建一个缓冲区，大小10
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        // 打印初始化缓冲区状态
        printByteBufferStatus("初始化状态：", byteBuffer);
        // 数据读到缓冲区
        channel.read(byteBuffer);
        printByteBufferStatus("数据读到缓冲区：", byteBuffer);
        // 准备操作前，所定操作范围（有的博文理解为写状态——>读状态）
        byteBuffer.flip();
        printByteBufferStatus("调用flip():", byteBuffer);
        // 判断是否有可读数据
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            System.out.print((char) b);
        }
        System.out.println();
        printByteBufferStatus("调用get():", byteBuffer);
        // 调用clear解锁（对应上边可理解读状态-->写状态）
        byteBuffer.clear();
        printByteBufferStatus("调用clear():", byteBuffer);
        // 关闭管道
        fileInputStream.close();
    }

    private static void printByteBufferStatus(String s, ByteBuffer byteBuffer) {
        System.out.println(s);
        // 容量大小
        System.out.println("容量大小capacity:" + byteBuffer.capacity());
        // 当前操作数据坐在位置，即游标信息
        System.out.println("游标信息position;" + byteBuffer.position());
        // 限制值，limit
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println();
    }
}
