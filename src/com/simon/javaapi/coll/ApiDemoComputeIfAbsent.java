package com.simon.javaapi.coll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiDemoComputeIfAbsent {
    public static void main(String[] args) {
        countWithFunction();

        buildMapping();


        countWords();


        classifyByFirstChar();

        System.out.println(getFactorial(5));
        System.out.println(getFactorial(5));
        System.out.println(getFactorial(10));



    }

    static Map<Integer, Long> factorialCache = new HashMap<>();

    /**
     * 缓存阶乘结果 computeIfAbsent()
     */
    private static long getFactorial(int n) {
        return factorialCache.computeIfAbsent(n, k -> {
            System.out.println("计算 " + k + "的阶乘...");
            long result = 1;
            for (int i = 2; i <= k; i++) {
                result *= i;
            }
            return result;
        });
    }
/**
 * 缓存阶乘结果 getOrDefault()
 */
    private static long getFactorialNew(int n) {
        if(!factorialCache.containsKey(n)) {
            System.out.println("计算 " + n + "的阶乘...");
            long result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            factorialCache.put(n, result);
            return result;
        }else {
            return factorialCache.get(n);
        }
    }

    private static void classifyByFirstChar() {
        // 按照首字母（大写）分组
        String[] words = {"Apple", "Banana", "Ant", "Ball", "Cat"};
        Map<Character, List<String>> groups = new HashMap<>();
        for(String word : words) {
            if(word.isEmpty()) continue;
            char firstChar = Character.toUpperCase(word.charAt(0));
            groups.computeIfAbsent(firstChar, k-> new ArrayList<>()).add(word);
        }
    }

    private static void countWords() {
        String text = "Hello world hello Java Java world";
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.toLowerCase().split(" ");
        for(String word: words) {
            wordCounts.computeIfAbsent(word, k -> 0);
            wordCounts.put(word, wordCounts.get(word) + 1);
        }
        System.out.println(wordCounts);
    }

    /**
     * 统计字符出现的次数
     */
    private static void countWithFunction() {
        Map<Character, Integer> countMap = new HashMap<>();
        char c = 'a';
        if(countMap.containsKey(c)) {
            countMap.put(c,countMap.get(c) + 1 );
        } else {
            countMap.put(c,1);
        }
        System.out.println(countMap.toString());

        char cc = 'b';
        countMap.put(cc, countMap.computeIfAbsent(cc, k-> 0) + 1);
        System.out.println(countMap.toString());
    }
    /**
     * 构建映射关系
     */
    private static void buildMapping() {
        Map<String, List<String>> groupMap = new HashMap<>();
        String key = "group1";
        String value = "item1";
        groupMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        System.out.println(groupMap); // {group1=[item1]}
        String valueP = "Pitem1";
        groupMap.computeIfAbsent(key, k -> new ArrayList<>()).add(valueP); // key存在，追加进ArrayList
        System.out.println(groupMap); // {group1=[item1, Pitem1]}
    }
}
