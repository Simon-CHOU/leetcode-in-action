package com.simon;

import com.simon.util.*;

import java.math.BigInteger;
import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
//        while (true) {
            String [] arr = new String []{"623986800","3","887298","695","794","6888794705","269409","59930972","723091307","726368","8028385786","378585"};
            int k = 11;
            String kth = solution.kthLargestNumber(arr, k);
            System.out.println(kth);
//        }
    }
}

class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        List<BigInteger> intList = new ArrayList<>();
        for (String num : nums) {
            intList.add(new BigInteger(num));
        }
        intList.sort(Collections.reverseOrder());
        return intList.get(k -1).toString();
    }
}