package com.stack;

/**
 * @Date 2019/2/25 16:28
 */
public interface Stack<T> {

    boolean isEmpty();

    void push(T data);

    T peek();

    T pop();
}
