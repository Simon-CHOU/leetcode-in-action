package com.simon;

import com.simon.util.InputUtil;
import com.simon.util.LinkedList;
import com.simon.util.ListNode;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String str = InputUtil.inputStr();
            System.out.println(solution.countSegments(str));
        }
    }


}


class Solution {
    public int countSegments(String s) {
        s = s.trim();
        if(s.equals("")) return 0;
        String[] split = s.split("\\s+");
        return split.length;
    }
}