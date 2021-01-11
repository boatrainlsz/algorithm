package com.lsz.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * 不包含重复元素
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    //添加元素e
    public void add(E e) {
        root = add(root, e);
    }

    //是否包含e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //前序遍历非递归写法
    public void preOrderIter() {
        //使用栈
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }


    //后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.e);
    }

    //层序遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public E findMinimum() {
        //最左端的节点就是最小值
        return findMinimum(root).e;
    }

    private Node findMinimum(Node node) {
        if (node.left == null) {
            //找到了
            return node;
        }
        return findMinimum(node.left);
    }

    public E findMaximum() {
        //最右端的节点就是最大值
        return findMaximum(root).e;
    }

    private Node findMaximum(Node node) {
        if (node.right == null) {
            //找到了
            return node;
        }
        return findMaximum(node.right);
    }


    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.compareTo(e) == 0) {
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }


    //以node为根节点的BST中添加元素e,返回添加后的根节点
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }
}