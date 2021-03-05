package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
//            TreeNode s = InputTreeUtil.inputBtree();
            int s = InputUtil.inputInt();
            DisplayArrayUtil.disp(solution.countBits(s));
        }
    }
}

class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & i - 1) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
}