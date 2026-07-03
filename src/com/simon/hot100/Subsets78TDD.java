package com.simon.hot100;

import java.util.*;

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

    }
}
