package com.simon.tw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(); // depth
        int[][] f = new int[n][n];
        Triangle.dispTwoDimention(triangle);
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            Triangle.printMatrix(f);
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
                Triangle.printMatrix(f);
            }
            f[i][i] = f[i - 1][i -1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for(int i = 1; i< n; ++i){
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }



    public int minimumTotalReduceSpatialComplexity(List<List<Integer>> triangle) {
        // 从根到i,j的路径和， f[i][j] = min(f[i - 1, j] , f[i - 1, j - 1]) + arr[i, j]
        int n = triangle.size(); // depth
        int[][] f = new int[2][n]; // dp arr 只有两行，当前计算结果在 第二行 i=1
        f[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++) {
            Triangle.printMatrix(f);
            // 最左边的一列，不用比较，直接加起来填表
            f[1][0] = f[0][0] + triangle.get(i).get(0);
            // triangle 当前，
            for(int j = 1; j < i; j++) {
                f[1][j] = Math.min(f[0][j], f[0][j - 1]) + triangle.get(i).get(j); // 错误f[1][j - 1], f[0][j - 1]
                Triangle.printMatrix(f);
            }
            // 右下角，不用比较，只有一条路
            f[1][i] = f[0][i - 1] + triangle.get(i).get(i);

            // 假设要交换第 0 行和第 1 行
            int[] temp = f[0];
            f[0] = f[1];
            f[1] = temp;
        }
        // 因为有 swap 两行，所以结果行在
        // 找最小
        // int minTotal = 0;//错误的初值
        int minTotal = f[0][0];
        for(int i = 1 ; i < n; i++) {
            minTotal = Math.min(minTotal, f[0][i]); // 遍历dp数组的最后一行，找最小（最优）结果
        }
        return minTotal;
    }


    public int minimumTotalOriginalArray(List<List<Integer>> triangle) {
        // 从根到i,j的路径和， f[i][j] = min(f[i - 1, j] , f[i - 1, j - 1]) + arr[i, j]
        Triangle.dispTwoDimention(triangle);
        int n = triangle.size(); // depth
        List<List<Integer>> t = triangle; // 创建饮用，缩短命名，以精简代码
        for (int i = 1; i < n; i++) {
            Triangle.dispTwoDimention(triangle);
            // 最左边的一列，不用比较，直接加起来填表
            t.get(i).set(0, t.get(i - 1).get(0) + triangle.get(i).get(0));
            Triangle.dispTwoDimention(triangle);
            // triangle 当前，
            for (int j = 1; j < i; j++) {
                triangle.get(i).set(j, Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1)) + triangle.get(i).get(j));
                Triangle.dispTwoDimention(triangle);
            }
            // 右下角，不用比较，只有一条路
            triangle.get(i).set(i, triangle.get(i - 1).get(i - 1) + triangle.get(i).get(i));

        }
        // 找最小
        // int minTotal = 0;//错误的初值
        int minTotal = triangle.get(n - 1).get(0);
        for (int i = 1; i < n; i++) {
            minTotal = Math.min(minTotal, triangle.get(n - 1).get(i)); // 遍历dp数组的最后一行，找最小（最优）结果
        }
        return minTotal;
    }
}

public class Triangle {
    public static void main(String[] args) {
        //triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        // 初始化 triangle
        List<List<Integer>> triangle = new ArrayList<>();

        // 逐个添加子列表
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
        // 打印 triangle 以验证
        System.out.println(triangle);


        Solution120 solution120 = new Solution120();

        System.out.println(solution120.minimumTotalOriginalArray(triangle));
    }

    static void dispTwoDimention(List<List<Integer>> myList){
        // 转换为二维数组
        int[][] myArray = new int[myList.size()][];
        for (int i = 0; i < myList.size(); i++) {
            List<Integer> subList = myList.get(i);
            myArray[i] = new int[subList.size()];
            for (int j = 0; j < subList.size(); j++) {
                myArray[i][j] = subList.get(j);
            }
        }
        printMatrix(myArray);
    }

    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Empty matrix");
            return;
        }

        int maxLen = 0;
        for (int[] row : matrix) {
            for (int val : row) {
                maxLen = Math.max(maxLen, String.valueOf(val).length());
            }
        }

        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%" + (maxLen + 1) + "d", val); // 格式化输出，保持对齐
            }
            System.out.println(); // 换行
        }
        System.out.println("=================");
        System.out.println("=================");
    }

}
