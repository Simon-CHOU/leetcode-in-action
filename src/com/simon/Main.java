package com.simon;

import com.simon.util.*;

import java.math.BigInteger;
import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
//        while (true) {
        String[] arr = new String[]{"623986800", "3", "887298", "695", "794", "6888794705", "269409", "59930972", "723091307", "726368", "8028385786", "378585"};
        int k = 11;
        String kth = solution.kthLargestNumber(arr, k);
        System.out.println(kth);
//        }
    }
}

class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        //  自定义比较函数，在 s1 对应的整数较大时返回 -1，反之返回 1
        Arrays.sort(nums, (x, y) -> {
            int xLen = x.length();
            int yLen = y.length();
            if (xLen != yLen) {
                return yLen - xLen; // 首先比较字符串长度
            }
            return y.compareTo(x);// 长度相等时比较字符串字典序大小
        });
        return nums[k - 1];
    }

    public String kthLargestNumber1(String[] nums, int k) {
        Arrays.sort(nums, (o1, o2) -> o2.length() - o1.length() == 0 ? o2.compareTo(o1) : o2.length() - o1.length());
        return nums[k - 1];
    }
}
