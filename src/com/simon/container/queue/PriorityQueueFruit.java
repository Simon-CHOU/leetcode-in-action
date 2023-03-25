package com.simon.container.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueFruit {
    public static void main(String[] args) {
        // PriorityQueue 就是实现了“VIP插队”的 FIFO Queue
        // https://www.liaoxuefeng.com/wiki/1252599548343744/1265120632401152
        Queue<String> q = new PriorityQueue<>();
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll());//apple
        System.out.println(q.poll());//banana
        System.out.println(q.poll());//pear
        System.out.println(q.poll());// null
        // reverse https://stackoverflow.com/questions/11804733/java-get-string-compareto-as-a-comparator-object
        Queue<String> q1 = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        q1.offer("apple");
        q1.offer("pear");
        q1.offer("banana");
        System.out.println(q1.poll());//pear
        System.out.println(q1.poll());//banana
        System.out.println(q1.poll());//apple
        System.out.println(q1.poll());// null
    }
}
