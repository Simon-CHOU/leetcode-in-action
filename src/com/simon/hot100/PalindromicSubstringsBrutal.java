package com.simon.hot100;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstringsBrutal {

    public int countSubstrings(String s) {
        // 有多少子串  sum n + (n-1） + (n - 2) ... 1  = ?
        List<String> candidate = new ArrayList<>();
        int n = s.length();
        for(int i =0; i< n; i++) { //???
            for (int j = i; j < n; j++) {
                    candidate.add(s.substring(i, j+1)); // substring 左闭右开，所以需要 j+1
            }
        }

        // validate 每个子串是否是回文
        int count = 0 ;
        for(String cc : candidate) {
            if(check(cc)) {
                count++;
            }
        }

        // 计数
          return count;
    }
    boolean check(String s) {
        if(s.isEmpty() || s.length() ==1) {
            return true;
        }
        for(int i = 0 ; i< s.length()/2; i++) {
            if(s.charAt(s.length() -1  -i) != s.charAt(i)) {
                return false;
            }
        }
        return true;
        // 0 1 2 3 4
        // 0 1 2 3 4 5
    }


    public static void main(String[] args) {
        PalindromicSubstringsBrutal s  = new PalindromicSubstringsBrutal();
        int abc = s.countSubstrings("abc");
        System.out.println(abc);


    }
}
