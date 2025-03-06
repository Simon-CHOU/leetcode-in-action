package com.simon.tw;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> cache;
    private int capacity;
    private Node head;
    private Node tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0, 0);
        head.next =tail;
        tail.prev = head; // 头尾相接

    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
       Node node =cache.get(key);
       if(node != null) {
          node.value = value;
          moveToHead(node);
       } else {
           Node newNode = new Node(key, value);// 创建新节点，删除原结点
           cache.put(key, newNode);
           addToHead(newNode);
           if(cache.size() > capacity) {
               Node last = removeTail();
               cache.remove(last.key);
           }
       }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next =node;
    }
    private Node removeTail() {
        Node last = tail.prev;
        removeNode(last);
        return last;
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4

    }
}
