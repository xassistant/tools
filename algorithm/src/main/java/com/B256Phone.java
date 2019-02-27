package com;

public class B256Phone {

    public static void main(String[] args) {

        int bitArrayIndex = (73 & (4 - 1)) + 1;
        int bitIndex = (73 - 64 * (bitArrayIndex - 1));
        System.out.println(bitArrayIndex + ":" + bitIndex);
    }
}