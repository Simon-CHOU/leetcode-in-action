package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] a = InputUtil.inputIntArray();
            System.out.println(solution.maxProfit(a));
        }
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                if (price - minPrice > maxProfit) {
                    maxProfit = price - minPrice;
                }
            }
        }
        return maxProfit;
    }
}