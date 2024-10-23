package com.simon.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  N（5<=N<=52）张扑克牌 判断是否存在同花顺
 *  花色：S H C D
 *  点数：A 2 3 4 5 6 7 8 9 10 J Q K
 *  方片A："DA"
 *  梅花10："C10"
 *  同花顺
 *  1. 5张扑克牌
 *  2. 花色相同
 *  3. 连成顺子
 *  A 2 3 4 5
 *  2 3 4 5 6
 *  ...
 *  9 10 J Q K
 *  10 J Q K A
 */
public class CardNoSortUtil {
    public static void main(String[] args) {
        boolean b = isStraightFlush(new String[]{"DA", "D2", "D3", "D4", "D5"}); //t
        System.out.println(b? "是" : "否");
        System.out.println(isStraightFlush(new String[]{"DA", "D3", "D2", "D4", "D5"})); // true
        System.out.println(isStraightFlush(new String[]{"SJ", "SK", "SA", "SQ", "S10"})); //TRUE
        System.out.println(isStraightFlush(new String[]{"SJ", "SK", "SA", "SQ", "S2"})); //FALSE
        System.out.println(isStraightFlush(new String[]{"DJ", "SK", "SA", "SQ", "S10"})); //FALSE
    }

    public static boolean isStraightFlush(String[] cards) {
        if (cards.length < 5) {
            return false;
        }

        // 花色映射
        Map<Character, Integer> suitMap = new HashMap<>();
        suitMap.put('S', 0);
        suitMap.put('H', 1);
        suitMap.put('C', 2);
        suitMap.put('D', 3);

        // 点数映射
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

        // 将扑克牌按花色和点数排序
        int[][] sortedCards = new int[cards.length][2];
        for (int i = 0; i < cards.length; i++) {
            sortedCards[i][0] = suitMap.get(cards[i].charAt(0));
            sortedCards[i][1] = rankMap.get(cards[i].substring(1));
        }

        // 使用冒泡排序对二维数组进行排序
        for (int i = 0; i < sortedCards.length; i++) {
            for (int j = 0; j < sortedCards.length - i - 1; j++) {
                if (sortedCards[j][0] > sortedCards[j + 1][0] ||
                        (sortedCards[j][0] == sortedCards[j + 1][0] && sortedCards[j][1] > sortedCards[j + 1][1])) {
                    int[] temp = sortedCards[j];
                    sortedCards[j] = sortedCards[j + 1];
                    sortedCards[j + 1] = temp;
                }
            }
        }

        // 检查是否为同花顺
        for (int i = 0; i < cards.length - 4; i++) {
            if (sortedCards[i][0] == sortedCards[i + 4][0] && // 花色相同
                    sortedCards[i + 4][1] - sortedCards[i][1] == 4) { // 点数连续
                return true;
            }
        }

        // 检查A-5的顺子
        if (sortedCards[0][1] == 1 && // A
                sortedCards[cards.length - 1][1] == 13 && // K
                sortedCards[cards.length - 2][1] == 12 && // Q
                sortedCards[cards.length - 3][1] == 11 && // J
                sortedCards[cards.length - 4][1] == 10) { // 10
            return true;
        }

        return false;
    }
}

