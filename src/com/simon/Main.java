package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            char[][] nums = InputUtil.matrixC2D(9, 9);
            System.out.println(solution.isValidSudoku(nums));
        }
    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        //数字 1-9 在每一行只能出现一次。
        for (int i = 0; i < 9; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        //数字 1-9 在每一列只能出现一次。
        for (int j = 0; j < 9; j++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        //数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
        //row1
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        map.clear();
        for (int j = 3; j < 6; j++) {
            for (int i = 0; i < 3; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        map.clear();
        for (int j = 6; j < 9; j++) {
            for (int i = 0; i < 3; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
//row 2
        map.clear();
        for (int j = 0; j < 3; j++) {
            for (int i = 3; i < 6; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        map.clear();
        for (int j = 3; j < 6; j++) {
            for (int i = 3; i < 6; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        map.clear();
        for (int j = 6; j < 9; j++) {
            for (int i = 3; i < 6; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        // row 3
        map.clear();
        for (int j = 0; j < 3; j++) {
            for (int i = 6; i < 9; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        map.clear();
        for (int j = 3; j < 6; j++) {
            for (int i = 6; i < 9; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        map.clear();
        for (int j = 6; j < 9; j++) {
            for (int i = 6; i < 9; i++) {
                if (board[i][j] == '.') continue;
                if (map.get(board[i][j]) != null) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }
        return true;
    }
}

/**
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
<p>
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-sudoku
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */