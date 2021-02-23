package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode s = InputTreeUtil.inputBtree();
            System.out.println(Arrays.toString(solution.findMode(s)));
        }
    }


}

class Solution {
    List<Integer> answer = new ArrayList<Integer>();
    int base, count, maxCount;

    public int[] findMode(TreeNode root) {
        dfs(root);
        return answer.stream().mapToInt(i->i).toArray();
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        update(root.val);
        dfs(root.right);
    }

    public void update(int x) {
        if (x == base) {
            count++;
        } else {//x>base  非递减
            base = x;
            count = 1;
        }
        if (count == maxCount) {
            answer.add(x);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(x);
        }
    }
}