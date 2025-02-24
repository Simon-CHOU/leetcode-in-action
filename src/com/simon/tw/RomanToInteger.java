package com.simon.tw;

import java.util.HashMap;
import java.util.Map;

class Solution13 {
    static final Map<Character, Integer> romanToArabic = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // char ch1 = s.charAt(i+1); // 往右看一位  如何避免越界
            if (i < s.length() - 1 && // 这里要判断最后一位
                    (romanToArabic.get(ch) < romanToArabic.get(s.charAt(i + 1)))) { // 不写临时变量，就可以避免越界

                ans -= romanToArabic.get(ch);
            } else {
                ans += romanToArabic.get(ch);
            }
        }
        return ans;
    }
}

public class RomanToInteger {
    public static void main(String[] args) {
        Solution13 s = new Solution13();
        System.out.println(s.romanToInt("MCMXCIV"));
    }
}
