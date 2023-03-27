package com.simon.container.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueSort {
    //    https://stackoverflow.com/questions/683041/how-do-i-use-a-priorityqueue
    public static void main(String[] args) {
        StringLengthComparator comparator = new StringLengthComparator();
        PriorityQueue<String> queue = new PriorityQueue<>(comparator);
        queue.add("short");
        queue.add("very long indeed");
        queue.add("medium");
        while (queue.size() != 0) {
            System.out.println(queue.remove());
        }
//        short
//        medium
//        very long indeed

        // java8 solution
//        new PriorityQueue<String>(5, (a,b)->{a.length() - b.length()}); // {} no need
        PriorityQueue<String> pq = new PriorityQueue<>(5, (a, b) -> a.length() - b.length());
        PriorityQueue<String> pq1 = new PriorityQueue<>(5, Comparator.comparingInt(String::length));
        pq1.add("Apple");
        pq1.add("PineApple");
        pq1.add("Custard Apple");
        while(pq1.size() != 0) {
            System.out.println(pq1.remove());
        }

    }
}

class StringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int len1 = o1.length();
        int len2 = o2.length();
        // pattern 1
        if (len1 < len2) {
            return -1;
        } else if (len1 > len2) {
            return 1;
        } else {
            return 0;
        }
//        // pattern 2
//        if (len1 < len2) {
//            return -1;
//        }
//        if(len1 > len2) {
//            return 1;
//        }
//        return 0;

//        return Integer.compare(o1.length(), o2.length());
    }
}