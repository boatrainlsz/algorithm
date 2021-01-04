package com.lsz.bst;

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
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    //以node为根节点的BST中添加元素e
    private void add(Node node, E e) {
        //------------------------递归的终止条件--------------------------//
        if (e.equals(node.e)) {
            //不包含重复元素
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;

        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }
        //------------------------递归的终止条件--------------------------//


        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
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