package com.simon.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Subsets78TDD {

    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
//        int depth = 0; // depth 是给全排列，到了n个数，下了第N层，再搜集结果用的判断条件；子集只需要遍历树（如何理解）？
        int startIndex= 0;
        dfs(nums, startIndex,  path, ans);
        return ans;
    }

    private void dfs(int[] nums, int startIndex, Deque<Integer> path, List<List<Integer>> ans) {
        if(startIndex>nums.length) {return;}
        ans.add(path.stream().toList());// 收集结果

        // 递归退出条件是什么？是“范围限制”
        for (int i = startIndex; i < nums.length; i++) { // startIndex 从这里开始循环
            path.addLast(nums[i]); // 子集 和 全排列，都要遍历 nums
            dfs(nums, i+1,path, ans); // startIndex -> i -> i++ 传递性 ； i+1 不是 i++！！！“先取值，后自增”
            path.removeLast();
        }
    }


    public static void main(String[] args) {
        Subsets78TDD solution = new Subsets78TDD();
        List<List<Integer>> subsets = solution.subsets(new int[]{1, 2, 3});
        subsets.forEach(System.out::println);

    }
}
