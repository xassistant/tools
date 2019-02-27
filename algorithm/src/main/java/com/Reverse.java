package com;

/**
 * @Date 2019/2/26 18:34
 */
public class Reverse {
    public static void main(String[] args) {

        Node a = new Node("a", null);
        Node b = new Node("b", null);
        a.setNext(b);
        // 链表反转需要定义一个上个节点，因为只有两个节点都知道才能转换位置
        Node last = null;
        while (a != null) {
            Node next = a.next;
            a.next = last;
            last = a;
            a = next;
        }
        System.out.println(last);

        Integer[] arr = {1, 2};
        for (int i = 0; i < arr.length - 1; i++) {
            int tmp;
            tmp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = tmp;
        }

        System.out.println(arr);

    }

    static class Node {
        String string;
        Node next;

        public Node(String string, Node next) {
            this.string = string;
            this.next = next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "string='" + string + '\'' + ", next=" + next + '}';
        }
    }
}
