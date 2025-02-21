package com.simon.tw;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

class Solution46 { // https://www.bilibili.com/video/BV1oa4y1v7Kz/  官方解答
    public List<List<Integer>> permute(int[] nums) { // https://leetcode.cn/problems/permutations/
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (len == 0) {
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<Integer>(); // 栈
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, ans);
        return ans;

    }

    void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> ans) {
        if (depth == len) {
            ans.add(new ArrayList<>(path)
            );// 拷贝 ，避免结果集出现空列表
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, ans);
            path.removeLast();
            used[i] = false;
        }

    }
}

public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 solution = new Solution46();
        List<List<Integer>> res = solution.permute(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }

    }
}
