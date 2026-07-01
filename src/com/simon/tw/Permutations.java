package com.simon.tw;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution46 { // https://www.bilibili.com/video/BV1oa4y1v7Kz/  官方解答
    public List<List<Integer>> permute(int[] nums) { // https://leetcode.cn/problems/permutations/
       //used
        // ans
        // dfs
        // path deque
        // depth
//        for-each

        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int len =  nums.length;
        boolean[] used = new boolean[len];
        int depth = 0;
        dfs(nums, depth, used,path, ans);
        return ans;
    }

    private void dfs(int[] nums, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> ans) {
        if(depth== nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i <nums.length; i++) {
            if(used[i]) continue; // 不要漏掉拦截（即使我还不知道漏掉拦截意味着什么本质
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, depth+1, used, path, ans);
            used[i] = false;
            path.removeLast();
        }
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
        test(List.of(List.of()), new int[]{}); //怎么写exp对象？

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
