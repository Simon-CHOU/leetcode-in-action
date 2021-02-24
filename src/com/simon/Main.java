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
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if(pre.right==null){
                pre.right = cur;
                cur = cur.left;
            }else{
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
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
//[1,null,2,2]
//[2]