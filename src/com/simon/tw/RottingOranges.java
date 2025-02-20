package com.simon.tw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution994 { //https://leetcode.cn/problems/rotting-oranges/

    private final static int[][] DIRECTION = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        // 计数，找到烂橘子坐标
        int ans = 0;
        int fresh = 0;
        List<int[]> location = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) fresh++;
                if (grid[i][j] == 2) {
//                    location.add(new int[i][j]);
//                    location.add(new int[1][1]{i,j});
                    location.add(new int[]{i, j}); //坐标，是一维数组，不能用二维数组
                }
            }
        }
        // Correctly print the contents of location
        displayGrid(grid, ans);
        // 循环天数
        while (fresh > 0 && !location.isEmpty()) {
            ans++;
            List<int[]> nextLocation = location; // 创建临时list，用于迭代
            location = new ArrayList<>();
            for (int[] rot : nextLocation) {
                for (int[] dir : DIRECTION) {
                    int i = rot[0] + dir[0]; // 横坐标
                    int j = rot[1] + dir[1]; // 纵坐标
//                    if (i > 0 && j > 0 && i < rows && j < rows && grid[i][j] == 1) {
                    if (i >= 0 && j >= 0 && i < rows && j < cols && grid[i][j] == 1) { // 这里需要等于0
                        fresh--;
                        grid[i][j] = 2;
                        location.add(new int[]{i, j});
                    }
                }
                // 遍历周围的格子，如果是1就加入下一轮检查的集合中
            }
            displayGrid(grid, ans);
        }

        // 反转橘子状态
        // 更新烂橘子坐标
        return  fresh > 0 ? -1 : ans;
    }

    private static void displayGrid(int[][] grid, int ans) {
        // display
        System.out.println("===========第"+ ans +"天==========");
        for(int[] g: grid) {
            System.out.println(Arrays.toString(g));
        }
    }
}


public class RottingOranges {
    public static void main(String[] args) {
        int[][] grid = {{0, 1}}; // -1
        Solution994 solution = new Solution994();
        System.out.println(solution.orangesRotting(grid));
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(solution.orangesRotting(grid1));//4
    }
}
