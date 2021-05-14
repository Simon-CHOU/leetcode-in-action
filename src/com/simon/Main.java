package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {

            String s = InputUtil.inputStr().trim();
            String substring = s.substring(2, s.length() - 2);
            String[] split = substring.split("],\\[");
            int[][] events = new int[split.length][2];
            for (int i = 0; i < split.length; i++) {
                String[] split1 = split[i].split(",");
                for (int j = 0; j < split1.length; j++) {
                    events[i][j] = Integer.parseInt(split1[j]);
                }
            }
            System.out.println(solution.maxEvents(events));
        }
    }
}

class Solution {

    public int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0, lastDay = 1, i = 0, n = events.length;
        while (i < n || !pq.isEmpty()) {
            while (i < n) {
                int start = events[i][0];
                if (start != lastDay) break;
                int end = events[i][1];
                i++;
                pq.offer(end);
            }
//            while (i < n && events[i][0] == lastDay) {
//                pq.offer(events[i++][1]);
//            }
            while (!pq.isEmpty() && pq.peek() < lastDay) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
            lastDay++;
        }
        return res;
    }
    //TIMEOUT
//    public int maxEvents(int[][] events) {
//        Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);
//        Set<Integer> set = new HashSet<>();
//        for (int[] event : events) {
//            int s = event[0];
//            int e = event[1];
//            for (int i = s; i <=e; i++) {
//                if (!set.contains(i)) {
//                    set.add(i);
//                    break;
//                }
//            }
//        }
//        return set.size();
//    }
}
