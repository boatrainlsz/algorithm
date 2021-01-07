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
