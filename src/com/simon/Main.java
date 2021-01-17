package com.simon;

import com.simon.util.InputUtil;
import com.simon.util.LinkedList;
import com.simon.util.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] ints = InputUtil.inputIntArray();
            int i = InputUtil.inputIntMatrix();
            System.out.println(solution.checkStraightLine(ints, i));
        }
    }


}


class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}