package com.link;


/**
 * @Date 2019/2/19 18:24
 */
public class LinkedTable {
    public static void main(String[] args) {
        LinkedOper linkedOper = new LinkedOper();
        linkedOper.add(1);
        linkedOper.add(2);
        linkedOper.add(3);
        System.out.println(linkedOper.getFirst().getData());
        System.out.println(linkedOper.getLast().getData());
    }

    static class LinkedOper<T> {
        Node<T> last;
        Node<T> first;
        int size = 0;

        public void add(T data) {
            final Node<T> l = last;
            final Node<T> newNode = new Node<>(data, l, null);
            last = newNode;
            if (l == null) {
                first = newNode;
            } else {
                last.next = newNode;
            }
            size++;
        }

        public Node<T> getFirst() {
            return first;
        }

        public Node<T> getLast() {
            return last;
        }
    }

    static class Node<T> {
        private T data;
        private Node<T> pre;
        private Node<T> next;

        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPre() {
            return pre;
        }

        public void setPre(Node<T> pre) {
            this.pre = pre;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
