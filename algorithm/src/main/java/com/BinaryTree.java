package com;

public class BinaryTree {
    BinaryTreeNode root;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        System.out.println(tree.find(4) + ":" + tree.find(11));
    }

    public int find(int data) {
        if (root == null) {
            return -1;
        }
        BinaryTreeNode currentNode = root;

        while (currentNode != null) {
            int currentData = currentNode.getiData();
            if (data == currentData) {
                return currentData;
            }
            if (data < currentData) {
                currentNode = currentNode.getLeftNode();
            } else {
                currentNode = currentNode.getRightNode();
            }
        }
        return -1;
    }

    public void insert(int i) {
        if (root == null) {
            root = new BinaryTreeNode(i, null, null);
            return;
        }
        BinaryTreeNode currentNode;
        currentNode = root;
        int currentData = currentNode.getiData();
        while (currentNode != null) {
            if (i < currentData) {
                BinaryTreeNode leftNode = currentNode.getLeftNode();
                if (leftNode == null) {
                    currentNode.setLeftNode(new BinaryTreeNode(i));
                }
                currentNode = leftNode;

            } else {
                BinaryTreeNode rightNode = currentNode.getRightNode();
                if (rightNode == null) {
                    currentNode.setRightNode(new BinaryTreeNode(i));
                }
                currentNode = rightNode;

            }
        }
    }

    static class BinaryTreeNode {
        public BinaryTreeNode(int iData, BinaryTreeNode leftNode, BinaryTreeNode rightNode) {
            this.iData = iData;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public BinaryTreeNode(int data) {
            this.iData = data;
        }

        public int getiData() {
            return iData;
        }

        public void setiData(int iData) {
            this.iData = iData;
        }

        public BinaryTreeNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(BinaryTreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public BinaryTreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(BinaryTreeNode rightNode) {
            this.rightNode = rightNode;
        }

        int iData;
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;

    }
}
