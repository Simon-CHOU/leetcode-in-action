package com.simon.interview.pdd;

import java.util.*;

public class PDDO010 {
    public static void main(String[] args) {
        // 测试用例1: 基本同花顺 DA D2 D3 D4 D5
        System.out.println("测试用例1: [\"DA\", \"D2\", \"D3\", \"D4\", \"D5\"]");
        boolean result1 = isStraightFlush(new String[]{"DA", "D2", "D3", "D4", "D5"});
        System.out.println("结果: " + result1 + " (期望: true) " + (result1 == true ? "PASS" : "FAIL"));

        // 测试用例2: 乱序的同花顺
        System.out.println("\n测试用例2: [\"DA\", \"D3\", \"D2\", \"D4\", \"D5\"]");
        boolean result2 = isStraightFlush(new String[]{"DA", "D3", "D2", "D4", "D5"});
        System.out.println("结果: " + result2 + " (期望: true) " + (result2 == true ? "PASS" : "FAIL"));

        // 测试用例3: 高牌同花顺
        System.out.println("\n测试用例3: [\"SJ\", \"SK\", \"SA\", \"SQ\", \"S10\"]");
        boolean result3 = isStraightFlush(new String[]{"SJ", "SK", "SA", "SQ", "S10"});
        System.out.println("结果: " + result3 + " (期望: true) " + (result3 == true ? "PASS" : "FAIL"));

        // 测试用例4: 同花但非顺子
        System.out.println("\n测试用例4: [\"SJ\", \"SK\", \"SA\", \"SQ\", \"S2\"]");
        boolean result4 = isStraightFlush(new String[]{"SJ", "SK", "SA", "SQ", "S2"});
        System.out.println("结果: " + result4 + " (期望: false) " + (result4 == false ? "PASS" : "FAIL"));

        // 测试用例5: 顺子但非同花
        System.out.println("\n测试用例5: [\"DJ\", \"SK\", \"SA\", \"SQ\", \"S10\"]");
        boolean result5 = isStraightFlush(new String[]{"DJ", "SK", "SA", "SQ", "S10"});
        System.out.println("结果: " + result5 + " (期望: false) " + (result5 == false ? "PASS" : "FAIL"));

        // 测试用例6: 包含A的特殊顺子 A 2 3 4 5
        System.out.println("\n测试用例6: [\"HA\", \"H2\", \"H3\", \"H4\", \"H5\", \"S6\"]");
        boolean result6 = isStraightFlush(new String[]{"HA", "H2", "H3", "H4", "H5", "S6"});
        System.out.println("结果: " + result6 + " (期望: true) " + (result6 == true ? "PASS" : "FAIL"));

        // 测试用例7: 包含A的特殊顺子 10 J Q K A
        System.out.println("\n测试用例7: [\"CA\", \"CJ\", \"CK\", \"CQ\", \"C10\", \"S9\"]");
        boolean result7 = isStraightFlush(new String[]{"CA", "CJ", "CK", "CQ", "C10", "S9"});
        System.out.println("结果: " + result7 + " (期望: true) " + (result7 == true ? "PASS" : "FAIL"));
        
        // 测试用例8: 不足5张牌
        System.out.println("\n测试用例8: [\"DA\", \"D2\", \"D3\"]");
        boolean result8 = isStraightFlush(new String[]{"DA", "D2", "D3"});
        System.out.println("结果: " + result8 + " (期望: false) " + (result8 == false ? "PASS" : "FAIL"));
    }

    /**
     * 判断给定的扑克牌中是否存在同花顺
     * 
     * @param cards 扑克牌数组，如["DA", "D2", "D3", "D4", "D5"]
     * @return 如果存在同花顺返回true，否则返回false
     */
    public static boolean isStraightFlush(String[] cards) {
        // 如果牌数少于5张，不可能有同花顺
        if (cards.length < 5) {
            return false;
        }

        // 定义花色映射
        Map<Character, Integer> suitMap = new HashMap<>();
        suitMap.put('S', 0); // 黑桃
        suitMap.put('H', 1); // 红桃
        suitMap.put('C', 2); // 梅花
        suitMap.put('D', 3); // 方片

        // 定义点数映射
        Map<String, Integer> rankMap = new HashMap<>();
        rankMap.put("A", 1);
        rankMap.put("2", 2);
        rankMap.put("3", 3);
        rankMap.put("4", 4);
        rankMap.put("5", 5);
        rankMap.put("6", 6);
        rankMap.put("7", 7);
        rankMap.put("8", 8);
        rankMap.put("9", 9);
        rankMap.put("10", 10);
        rankMap.put("J", 11);
        rankMap.put("Q", 12);
        rankMap.put("K", 13);

        // 按花色分组存储点数
        Map<Integer, Set<Integer>> suitRanks = new HashMap<>();
        for (String card : cards) {
            char suitChar = card.charAt(0);
            String rankStr = card.substring(1);
            
            int suit = suitMap.get(suitChar);
            int rank = rankMap.get(rankStr);
            
            suitRanks.computeIfAbsent(suit, k -> new HashSet<>()).add(rank);
        }

        // 检查每种花色是否存在同花顺
        for (Set<Integer> ranks : suitRanks.values()) {
            // 如果同一种花色的牌少于5张，跳过
            if (ranks.size() < 5) {
                continue;
            }
            
            // 检查是否存在同花顺
            if (containsStraight(ranks)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 检查给定的点数集合中是否存在顺子
     * 
     * @param ranks 点数集合
     * @return 如果存在顺子返回true，否则返回false
     */
    private static boolean containsStraight(Set<Integer> ranks) {
        List<Integer> sortedRanks = new ArrayList<>(ranks);
        Collections.sort(sortedRanks);
        
        // 检查普通顺子 (5张连续的牌)
        for (int i = 0; i <= sortedRanks.size() - 5; i++) {
            boolean isStraight = true;
            for (int j = 0; j < 4; j++) {
                if (sortedRanks.get(i + j + 1) - sortedRanks.get(i + j) != 1) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                return true;
            }
        }
        
        // 特殊处理包含A的顺子: A 2 3 4 5 和 10 J Q K A
        // 检查A 2 3 4 5的情况
        if (sortedRanks.contains(1) && sortedRanks.contains(2) && 
            sortedRanks.contains(3) && sortedRanks.contains(4) && 
            sortedRanks.contains(5)) {
            return true;
        }
        
        // 检查10 J Q K A的情况
        if (sortedRanks.contains(1) && sortedRanks.contains(10) && 
            sortedRanks.contains(11) && sortedRanks.contains(12) && 
            sortedRanks.contains(13)) {
            return true;
        }
        
        return false;
    }
}