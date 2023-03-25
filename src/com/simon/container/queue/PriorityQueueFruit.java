package com.simon.container.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueFruit {
    public static void main(String[] args) {
//        fruitDemo();
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); //Boss/V1
        System.out.println(q.poll()); //Bob/A1
        System.out.println(q.poll()); //Alice/A2
        System.out.println(q.poll()); //null
    }

    private static void fruitDemo() {
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
        // 要放入的元素并没有实现Comparable接口 e.g. String，则需要自己实现Comparator
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

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return name + "/" + number;
    }
}

class UserComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        if (u1.number.charAt(0) == u2.number.charAt(0)) {
            //首字符相同
            return u1.number.compareTo(u2.number);
        }
        if (u1.number.charAt(0) == 'V') {
            return -1; // V 优先级高
        } else {
            return 1;
        }
    }
}