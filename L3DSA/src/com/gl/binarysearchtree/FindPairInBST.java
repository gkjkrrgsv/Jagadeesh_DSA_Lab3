package com.gl.binarysearchtree;

import java.util.Stack;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class FindPairInBST {
    private Node root;

    public FindPairInBST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void findPairWithSum(Node node, int sum) {
        if (node == null)
            return;

        Node left = node, right = node;
        Stack<Node> leftStack = new Stack<>();
        Stack<Node> rightStack = new Stack<>();

        while (true) {
            while (left != null) {
                leftStack.push(left);
                left = left.left;
            }

            while (right != null) {
                rightStack.push(right);
                right = right.right;
            }

            if (leftStack.isEmpty() || rightStack.isEmpty())
                break;

            Node leftTop = leftStack.peek();
            Node rightTop = rightStack.peek();

            if (leftTop.key >= rightTop.key)
                break;

            int currentSum = leftTop.key + rightTop.key;

            if (currentSum == sum) {
                System.out.println("Sum = " + sum);
                System.out.println("Pair is (" + leftTop.key + "," + rightTop.key + ")");
                return;
            } else if (currentSum < sum) {
                left = leftTop.right;
                leftStack.pop();
            } else {
                right = rightTop.left;
                rightStack.pop();
            }
        }

        System.out.println("Nodes are not found.");
    }

    public static void main(String[] args) {
        FindPairInBST tree = new FindPairInBST();

        tree.insert(10);
        tree.insert(20);
        tree.insert(40);
        tree.insert(50);
        tree.insert(70);
        tree.insert(60);
        tree.insert(30);

        int sum = 130;
        tree.findPairWithSum(tree.root, sum);
    }
}
