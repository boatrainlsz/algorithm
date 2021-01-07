package com.lsz.trie;

import java.util.TreeMap;

public class Trie {

    private Node root;

    //trie中存储的单词个数
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    //添加一个单词word
    public void add(String word) {
        Node cur = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            //如果之前没存储过这个单词
            //表示一个单词存储完毕
            cur.isWord = true;
            size++;
        }
    }

    //添加一个单词word,递归方式
    public void addR(String word) {
        addR(word, 0, root);
    }

    //将word[index,n-1]添加到以root为根的Trie中去 todo 待验证
    private void addR(String word, int index, Node root) {
        char c = word.charAt(index);
        if (root.next.get(c) == null) {
            root.next.put(c, new Node());
        }
        if (index == word.length() - 1) {
            if (!root.next.get(c).isWord) {
                //如果之前没存储过这个单词
                //表示一个单词存储完毕
                root.next.get(c).isWord = true;
                size++;
            }
            return;
        }
        addR(word, index + 1, root.next.get(c));
    }

    private static class Node {
        //当前节点是否是单词的结尾
        public boolean isWord;
        //下一个节点
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }
}
