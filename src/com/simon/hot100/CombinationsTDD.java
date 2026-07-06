package com.simon.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationsTDD {
    public List<List<Integer>> combine(int n, int k) {
       //brute gen
        // k = depth, dfs n ?
        // collect ans
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int depth=0, start = 1; // 从1 ~n // 不需要 depth 因为 path.len 可以当depth
 backtrace(n, k , start, path, ans );
        return ans;
    }

    private void backtrace(int n, int k, int start, Deque<Integer> path, List<List<Integer>>  ans) {
        if(k== path.size()) {ans.add(path.stream().toList());

            return; //剪枝，一定要加return
        }

        int upperLimit = n - (k - path.size())  +1 ;
        for (int i = start; i <= upperLimit; i++) { // 勿忘<=等于  // 不剪枝性能恶劣        for (int i = start; i <= n; i++) { // 勿忘<=等于
            // choose
            path.add(i);
            // recursive
            backtrace(n, k,  i+1, path, ans); // backtrace(n, ++k, i, path, ans); k 和 n 都是输入； i 才是步长
            // 为什么不能是++i? 因为I还要在for循环中步进，++i篡改了值；我需要的其实是一个独立的临时变量 temp = i+1；传递temp到递归函数
            // redo
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationsTDD so = new CombinationsTDD();
        List<List<Integer>> combine = so.combine(100, 2);
        combine.forEach(System.out::println);
    }
}
