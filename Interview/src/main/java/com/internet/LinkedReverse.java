package com.internet;

import java.util.Stack;

/**
 * @Date 2019/2/18 11:00
 * <p>
 * 链表逆转
 */
public class LinkedReverse {

    static class Node<T> {
        T node;
        Node next;

        public T getNode() {
            return node;
        }

        public void setNode(T node) {
            this.node = node;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "node=" + node + ", next=" + next + '}';
        }
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node();
        node.setNode(1);

        Node<Integer> node1 = new Node();
        node1.setNode(2);
        node1.setNext(node);

        Node<Integer> node2 = new Node();
        node2.setNode(3);
        node2.setNext(node1);

        System.out.println(node2);

        // reverse
        Node reverse = reverse(node2);
        // 普通链表转换
        System.out.println(reverse);
        // 栈转换
        System.out.println(stackReverse(reverse));
    }

    private static Node stackReverse(Node<Integer> node) {
        Stack<Node<Integer>> stack = new Stack();
        while (node != null) {
            stack.push(node);
            node = node.getNext();
        }
        // 出栈
        if (stack.isEmpty()) {
            return null;
        }
        Node<Integer> res = stack.pop();
        Node<Integer> newNode = res;
        while (!stack.isEmpty()) {
            Node pop = stack.pop();

            pop.setNext(null);
            newNode.setNext(pop);
            newNode = pop;
        }
        return res;
    }

    private static Node reverse(Node<Integer> node) {
        Node next = null;
        while (node != null) {
            Node tmp = node.getNext();
            node.setNext(next);
            next = node;
            node = tmp;
        }
        return next;
    }
}
