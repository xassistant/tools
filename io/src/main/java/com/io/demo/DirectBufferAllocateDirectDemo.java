package com.io.demo;

import java.nio.ByteBuffer;

public class DirectBufferAllocateDirectDemo {
    // -verbose:gc -XX:+PrintGCDetails -XX:MaxDirectMemorySize=40M
    // 加上-XX:+DisableExplicitGC,也会报OOM(Direct buffer memory)
    public static void main(String[] args) {
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
         }
    }
}