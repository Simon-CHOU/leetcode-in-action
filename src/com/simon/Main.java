package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] nums = InputUtil.inputIntArray();
            DisplayArrayUtil.disp(solution.plusOne(nums));
        }
    }
}

class Solution {
    /**
     * 关键：只加1.
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;//除了9 加1进位(且原位为0），其他都不进位
            if (digits[i] != 0) return digits;
        }
        digits = new int[len + 1];//处理99,999..等特殊情况，进位1，且其余都是0
        digits[0] = 1;
        return digits;
    }
}
