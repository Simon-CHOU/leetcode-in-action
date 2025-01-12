package com.simon.weekly;

import java.util.ArrayList;
import java.util.List;

public class ZigzagGridTraversalWithSkip {

    //    public List<Integer> ZigzagGridTraversalWithSkip(int[][] grid) {
//        List<Integer> result = new ArrayList<>();
//        if (grid == null || grid.length == 0 || grid[0].length == 0) {
//            return result;
//        }
//
//        int m = grid.length;
//        int n = grid[0].length;
//        boolean leftToRight = true;
//
//        for (int i = 0; i < m; i++) {
//            if (leftToRight) {
//                // 从左到右遍历
//                for (int j = 0; j < n; j += 2) {
//                    result.add(grid[i][j]);
//                }
//            } else {
//                // 从右到左遍历
//                for (int j = n - 1; j >= 0; j -= 2) {
//                    result.add(grid[i][j]);
//                }
//            }
//            // 切换方向
//            leftToRight = !leftToRight;
//        }
//
//        return result;
//    }
    public List<Integer> zigzagTraversal(int[][] grid) {

        List<Integer> res = new ArrayList<>();
        if (grid == null  || grid.length == 0 || grid[0].length == 0) {
            return res;// empty list
        }
        int col = grid[0].length;
        int row = grid.length;
        boolean zig = true;

        for (int i = 0; i < row; i++) {
            if (zig) {
                for (int j = 0; j < col; j += 2) { // skip
                    res.add(grid[i][j]);
                }
            } else {
                for (int j = col - 1; j >= 0; j -= 2) { // skip
                    res.add(grid[i][j]);
                }
            }
            zig = !zig;
        }

        return res; // full list
    }

    public static void main(String[] args) {
        ZigzagGridTraversalWithSkip solution = new ZigzagGridTraversalWithSkip();
//        int[][] grid = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12}
//        };
//        List<Integer> result = solution.zigzagTraversal(grid);
//        System.out.println(result);  // 输出: [1, 3, 8, 6, 9, 11]

        int[][] grid2 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<Integer> result2 = solution.zigzagTraversal(grid2);
        // exp [1,3,5,7,9]
        // act [1,3,6,4,7,9]
        System.out.println(result2); // !! 不对，周赛
    }
}
