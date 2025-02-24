package com.simon.tw;

import java.util.HashSet;
import java.util.Set;

class Solution2395 {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i] + nums[i+1];
            if(set.contains(sum)) return true;
            set.add(sum);
        }
        return false;
    }
}
public class FindSubarraysWithEqualSum {

    public static void main(String[] args) {
        Solution2395 solution2395 = new Solution2395();
        System.out.println(solution2395.findSubarrays(new int[]{4,2,4}));
    }
}
