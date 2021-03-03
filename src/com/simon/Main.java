package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode s = InputTreeUtil.inputBtree();
            System.out.println(solution.zigzagLevelOrder(s));
        }
    }
}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, ans, 0);
        return ans;
    }

    /**
     * DFS遍历本身与正常的DFS并无区别，改造在于带着level+List，维护要输出的答案
     *
     * @param node  当前结点
     * @param ans   目标List
     * @param level 当前层数
     */
    void dfs(TreeNode node, List<List<Integer>> ans, int level) {
        if (node == null) {
            return;
        }
        if (ans.size() <= level) {//ans.size与层数对应，ans.size()<=level,即下一层and的List还未创建
            List<Integer> newLevel = new LinkedList<>();
            ans.add(newLevel);
        }
        Deque<Integer> list = new ArrayDeque<>(ans.get(level));
        if (level % 2 == 0) {//层数的奇偶，控制zigzag
//            list.off(node.val);//偶数层从做到右
            list.offerLast(node.val);
        } else {
//            list.add(0, node.val);//add(0,val)在首位插入，跟双端队列是一回事
            list.offerFirst(node.val);
        }
        ans.set(level, new ArrayList<>(list));
        dfs(node.left, ans, level + 1);
        dfs(node.right, ans, level + 1);

    }
}
//[3,9,20,null,null,15,7]