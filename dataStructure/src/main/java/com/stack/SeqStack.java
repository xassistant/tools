package com.stack;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author admin
 * @Date 2019/2/25 16:30
 */
public class SeqStack<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID = -2397689679638338188L;

    private int top = -1;

    private T[] array;

    private int capacity = 10;

    public SeqStack() {
        array = (T[]) new Object[capacity];
    }

    public SeqStack(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public void push(T data) {
        if (this.top >= array.length - 1) {
            grow();
        }
        array[++top] = data;
    }

    private void grow() {
        this.array = Arrays.copyOf(array, array.length << 1);
    }

    @Override
    public T peek() {
        if (this.top == -1) {
            return null;
        }
        return array[top];
    }

    @Override
    public T pop() {
        if (this.top == -1) {
            return null;
        }
        return array[top--];
    }

    public static void main(String[] args) {
        SeqStack<Integer> stack = new SeqStack<>();
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        System.out.println(stack.top + ":" + stack.pop());
        System.out.println(stack.top + ":" + stack.peek());
    }
}
