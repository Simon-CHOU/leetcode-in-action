package com.simon.tw;

class Solution299 {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int[] cntS = new int[10];//分别统计 secret 和 guess 的各个字符的出现次数 0~9
        int[] cntG = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++bulls;
            } else {
                ++cntS[secret.charAt(i) - '0'];
                ++cntG[guess.charAt(i) - '0'];
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; ++i) {
            cows += Math.min(cntS[i], cntG[i]);
        }//由于多余的数字无法匹配，对于 0 到 9 的每位数字，应取其在 secret 和 guess 中的出现次数的最小值
        return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
    }
}

public class BullsAndCows {
    public static void main(String[] args) {
        Solution299 s = new Solution299();
        System.out.println(s.getHint("1807", "7810")); //1A3B
    }
}
