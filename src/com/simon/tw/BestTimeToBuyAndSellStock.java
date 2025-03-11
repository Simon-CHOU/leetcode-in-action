package com.simon.tw;

class Solution121 {
        public int maxProfit(int[] prices) {
        int maxValue = 0;
        int minPrice= Integer.MAX_VALUE;
        // O(n) 一次遍历
        for(int price : prices) {
            if(minPrice > price) {
                minPrice = price;
            } else if( (price-minPrice) > maxValue) {
                maxValue = price-minPrice;
            }
        }

        return maxValue;
    }


    public int maxProfitTle(int[] prices) {
        int maxValue = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxValue) {
                    maxValue = prices[j] - prices[i];
                    System.out.println("i=" + i + " j=" + j + " maxValue=" + maxValue);
                }
            }
        }
        return maxValue;
    }
}

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution121 solution = new Solution121();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit(prices)); //5
    }
}
