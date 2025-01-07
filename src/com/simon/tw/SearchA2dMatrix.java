package com.simon.tw;

class Solution11 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = -1; // 二分 左
        int right = m * n; // 二分 右
        while(left + 1 < right) {
            int mid = (left+right) >>>1; //>>> 1 等价于将数值除以 2 并向下取整，同时忽略符号位的影响。
            int x = matrix[mid / n][mid % n];
            if(x == target) {
                return true;
            }
            if(x < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return false;
    }
}// m[x / row][x % col]
// a[9] = m[9/4][9 mod 4]  = m[2][1]
public class SearchA2dMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        Solution11 solution11 = new Solution11();
        boolean resBool = solution11.searchMatrix(matrix, 30);
        System.out.println(resBool);
    }
}
