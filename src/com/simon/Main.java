package com.simon;

import com.simon.util.InputUtil;
import com.simon.util.LinkedList;
import com.simon.util.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String str = InputUtil.inputStr();
            System.out.println(solution.lengthOfLastWord(str));
        }
    }


}


class Solution {
    public int lengthOfLastWord(String s) {
        StringTokenizer st = new StringTokenizer(s);
        List<String> res = new ArrayList<>();
        while (st.hasMoreTokens()) {
            res.add(st.nextToken());
        }
        if(res.size()==0){
            return 0;
        }
        return res.get(res.size() - 1).length();
    }
}
