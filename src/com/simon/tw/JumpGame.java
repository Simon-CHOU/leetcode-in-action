package com.simon.tw;

class Solution55 {
    /**
     * 请添加日志打印，以较高的可读性输出循环体中每一个关键变量的值和赋值情况
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int cover = 0;
        System.out.printf("初始覆盖范围: cover=%d%n", cover);

        for (int i = 0; i <= cover && i < nums.length; i++) {
            System.out.printf("%n---- 第 %d 次循环 ----%n", i);
            System.out.printf("当前索引: i=%d | 数值: nums[%d]=%d%n", i, i, nums[i]);
            System.out.printf("预计算步长: i + nums[i] = %d + %d = %d%n", i, nums[i], i + nums[i]);

            int newCover = Math.max(i + nums[i], cover);
            System.out.printf("更新覆盖范围: max(原cover=%d, 新步长=%d) → cover=%d%n", cover, i + nums[i], newCover);
            cover = newCover;

            if (cover >= nums.length - 1) {
                System.out.printf("√ 覆盖终点: cover=%d >= 终点索引=%d%n", cover, nums.length - 1);
                return true;
            }
            System.out.printf("未达终点，继续循环。下一索引将尝试: i+1=%d (当前cover=%d)%n", i + 1, cover);
        }

        System.out.printf("%n最终覆盖范围: cover=%d | 数组长度=%d%n", cover, nums.length);
        return false;
    }

    public boolean canJumpOri(int[] nums) {
        // 贪心算法，怎么跳跃不重要，关键在覆盖范围 | LeetCode：55.跳跃游戏_2023-01-20_@代码随想录_BV1VG4y1X7kB
        // cover 覆盖范围
        if (nums.length == 1) return true;
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(i + nums[i], cover);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}

public class JumpGame {
    public static void main(String[] args) {
        Solution55 solution55 = new Solution55();
        System.out.println(solution55.canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(solution55.canJump(new int[]{3, 2, 1, 0, 4})); // false
        System.out.println(solution55.canJump(new int[]{2, 0})); // true
    }
}
