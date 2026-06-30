package com.simon.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution46 { // https://www.bilibili.com/video/BV1oa4y1v7Kz/  官方解答
    public List<List<Integer>> permute(int[] nums) { // https://leetcode.cn/problems/permutations/
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (len == 0) {
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<>(); // 栈
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, ans);
        return ans;

    }

    record Trace(int depth) {

        private String indent() {
            return "│   ".repeat(depth);
        }

        void call(Deque<Integer> path) {
            System.out.printf("%sCALL   dfs(depth=%d, path=%s)%n",
                    indent(), depth, path);
        }

        void choose(int num) {
            System.out.printf("%s├── CHOOSE %d%n",
                    indent(), num);
        }

        void found(Deque<Integer> path) {
            System.out.printf("%s└── FOUND  %s%n",
                    indent(), path);
        }

        void undo(int num) {
            System.out.printf("%s├── UNDO   %d%n",
                    indent(), num);
        }

        void ret() {
            System.out.printf("%sRETURN%n",
                    indent());
        }
    }

    void dfs(int[] nums,
             int len,
             int depth,
             Deque<Integer> path,
             boolean[] used,
             List<List<Integer>> ans) {

        Trace trace = new Trace(depth);

        trace.call(path);

        if (depth == len) {
            trace.found(path);
            ans.add(new ArrayList<>(path));
            trace.ret();
            return;
        }

        for (int i = 0; i < len; i++) {

            if (used[i]) {
                continue;
            }

            trace.choose(nums[i]);

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, len, depth + 1, path, used, ans);

            path.removeLast();
            used[i] = false;

            trace.undo(nums[i]);
        }

        trace.ret();
    }
}
public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        test(List.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(2, 1, 3),
                List.of(2, 3, 1),
                List.of(3, 1, 2),
                List.of(3, 2, 1)), nums);
        test(List.of(
                List.of(0,1),
                List.of(1, 0)), new int[]{0,1});
        test(List.of(
                List.of(1)), new int[]{1});
        test(List.of(), new int[]{}); //怎么写exp对象？

    }

    private static void test(List<List<Integer>> exp, int[] nums ) {
        Solution46 solution = new Solution46();
        List<List<Integer>> act = solution.permute(nums);

        if(exp.equals(act)) { // 判断全等
            System.out.print("PASS!");
            printPermutations(act);
        }else {
            System.out.print("FAIL!");
            System.out.println("Expect: ");
            printPermutations(exp);
            System.out.println("Actual: ");
            printPermutations(act);

        }
    }
    private static void printPermutations(List<List<Integer>> res) {
        if(res.isEmpty()) {
            System.out.println("[]");
        }
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
