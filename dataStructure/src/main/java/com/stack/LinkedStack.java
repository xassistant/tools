package com.stack;

import java.io.Serializable;

/**
 * @Date 2019/2/25 19:14
 */
public class LinkedStack<T> implements Stack<T>, Serializable {
    private static final long serialVersionUID = 9133477984766258162L;

    private Node<T> last;

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public void push(T data) {
        Node<T> n = last;
        if (n == null) {
            n = new Node<>(data, null);
        } else {
            n = new Node<>(data, last);
        }
        last = n;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return last.getData();
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }

        T data = last.getData();
        last = last.next;
        return data;
    }

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        System.out.println(stack.pop() + ":" + stack.peek());
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
