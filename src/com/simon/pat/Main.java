package com.simon.pat;

import java.util.*;

/**
 * <a href="https://pintia.cn/problem-sets/994805342720868352/exam/problems/type/7?problemSetProblemId=994805526272000000">1002 A+B for Polynomials</a>
 * <pre>
 *  2 1 2.4 0 3.2
 *  2 2 1.5 1 0.5
 *  3 2 1.5 1 2.9 0 3.2
 *
 *  系数低消的corner case
 *  2 1 2.4 0 3.2
 *  2 2 1.5 1 -2.4
 *  2 2 1.5 0 3.2
 * </pre>
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        Map<Integer, Double> polyMap = new TreeMap<>(Collections.reverseOrder());//new HashMap<>();
        for (int i = 0; i < k; i++){
            int e = scanner.nextInt();
            double n = scanner.nextDouble();
            polyMap.put(e,n);
        }
        int k1 = scanner.nextInt();
        for (int i = 0; i < k1; i++){
            int e = scanner.nextInt();
            double n = scanner.nextDouble();
            polyMap.put(e,n + polyMap.getOrDefault(e, 0.0));
        }
//        System.out.print(polyMap.size() +" ");
//        for(Map.Entry<Integer, Double> v: polyMap.entrySet()) {
//            System.out.print(v.getKey() + " " + v.getValue());
//        }

        System.out.print(polyMap.values().stream()
                .filter(entry -> entry != 0)
                .count()); //系数为0，不计入项数
        for(Map.Entry<Integer, Double> v: polyMap.entrySet()) {
//            System.out.print(" " +v.getKey() + " " + v.getValue());
            if(v.getValue()!=0) {
                System.out.printf(" %d %.1f", v.getKey(), v.getValue()); // 题目要求保留1位小数 Please be accurate to 1 decimal place.
            }
        }
        scanner.close();
    }
}
