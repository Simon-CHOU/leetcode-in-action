package com.simon.hot100;

import java.util.*;

public class Subsets78TDD {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int startIndex = 0;
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, startIndex, path, ans);
        return ans;
    }

    private void dfs(int[] nums, int startIndex, Deque<Integer> path, List<List<Integer>> ans) {
        // 我又忘了递归退出条件
        ans.add(path.stream().toList());
        for (int i =startIndex; i < nums.length; i++) {  // 写反了     for (int i = 0; i < startIndex; i++) {
            path.add(nums[i]);
            dfs(nums,i+1, path, ans);
            path.removeLast();
        }
    }


    static void  test (List<List<Integer>> exp, int[] input) {
        Subsets78TDD solution = new Subsets78TDD();
        List<List<Integer>> subsets = solution.subsets(input);
        if(new HashSet<>(subsets).equals(new HashSet<>(exp))) { // 归一化 normalize，避免顺序影响；
            System.out.println("PASS!");
            subsets.forEach(System.out::println);
        } else {
            System.out.print("FAIL!");
            subsets.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> exp = List.of(
                List.of(),
                List.of(1),
                List.of(2),
                List.of(1,2),
                List.of(3),
                List.of(1,3),
                List.of(2,3),
                List.of(1,2,3)
        );
        test(exp, new int[]{1, 2, 3});
        // 输入：nums = [1,2,3]
        //输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

        List<List<Integer>> exp2 = List.of(
                List.of(),
                List.of(0)
        );
        test(exp2, new int[]{0});
        // 输入：nums = [0]
        //输出：[[],[0]]

    }
}
