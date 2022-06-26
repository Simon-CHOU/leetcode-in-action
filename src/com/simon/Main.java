package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] arr = InputUtil.inputIntArray();// [2,7,11,15]
            int target = InputUtil.inputInt(); // 9
            int[] ints = solution.twoSum(arr, target);
            DisplayArrayUtil.disp(ints); //exp [1,0]
        }
    }
}

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map  = new HashMap<>();
        for(int i = 0; i< numbers.length; i++) {
            System.out.println("# i="+ i + ", target - numbers[i] = " + (target - numbers[i]));
            if(map.containsKey(target - numbers[i])) {
                return new int[]{ map.get(target - numbers[i]) , i + 1 };
            }
            System.out.println("# i=" + i+ ",numbers[i]=" +numbers[i] );
            map.put(numbers[i], i  + 1);
        }
        return null;
    }
}