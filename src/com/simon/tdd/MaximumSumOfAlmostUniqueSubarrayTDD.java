package com.simon.tdd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解决"几乎唯一子数组的最大和"问题
 * 
 * 算法思路:
 * 1. 使用滑动窗口技术遍历所有长度为k的子数组
 * 2. 使用HashMap维护窗口内每个元素的出现次数
 * 3. 当窗口内不同元素个数 >= m时，计算窗口元素和并更新最大值
 * 
 * 时间复杂度: O(n)，其中n是数组长度
 * 空间复杂度: O(k)，哈希表最多存储k个不同的元素
 */
public class MaximumSumOfAlmostUniqueSubarrayTDD {

    /**
     * 计算满足条件的子数组的最大和
     * 
     * @param nums 输入的整数列表
     * @param m 子数组中至少需要的不同元素个数
     * @param k 子数组的长度
     * @return 满足条件的子数组的最大和，如果不存在满足条件的子数组则返回0
     */
    public long maxSum(List<Integer> nums, int m, int k) {
//20250803( 补) self recall
        long curSum = 0L;
        long maxSum = 0L;

        HashMap<Long, Integer> eleMap = new HashMap<>();

        for (int i = 0; i < k; i++) {
            long n = nums.get(i);
            eleMap.put(n, eleMap.getOrDefault(n, 0) + 1);
            curSum += n;
        }

        if (eleMap.size() >= m) { //如果 nums 的一个子数组有至少 m 个互不相同的元素，！等于也是
            maxSum = curSum;
        }

        for (int i = k; i < nums.size(); i++) {

            long left = nums.get(i - k);
            eleMap.put(left, eleMap.get(left) - 1);
            if (eleMap.get(left) == 0) { //如果某个元素个数变为0，则从哈希表中删除该元素，避免size()统计到计数为0的元素
                eleMap.remove(left);
            }
            curSum -= left;

            long right = nums.get(i);
            eleMap.put(right, eleMap.getOrDefault(right, 0) + 1);
            curSum += right;

            if (eleMap.size() >= m) {
                maxSum = Math.max(maxSum, curSum);
            }

        }

        return maxSum;
    }
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        MaximumSumOfAlmostUniqueSubarrayTDD solution = new MaximumSumOfAlmostUniqueSubarrayTDD();
        // 测试用例0
        // 输入: nums = [1], m = 1, k = 1
        // 输出: 1
        List<Integer> nums0 = List.of(1);
        long result0 = solution.maxSum(nums0, 1, 1);
        long expected0 = 1;
        System.out.println("测试用例0结果: " +  result0+ ", 期望: " + expected0);
        if (result0 == expected0) {
            System.out.println("测试用例0: 通过");
        } else {
            System.out.println("测试用例0: 失败");
        }

        // 测试用例1
        // 输入: nums = [2,6,7,3,1,7], m = 3, k = 4
        // 输出: 18
        List<Integer> nums1 = List.of(2, 6, 7, 3, 1, 7);
        long result1 = solution.maxSum(nums1, 3, 4);
        long expected1 = 18;
        System.out.println("测试用例1结果: " + result1 + ", 期望: " + expected1);
        if (result1 == expected1) {
            System.out.println("测试用例1: 通过");
        } else {
            System.out.println("测试用例1: 失败");
        }
        
        // 测试用例2
        // 输入: nums = [5,9,9,2,4,5,4], m = 1, k = 3
        // 输出: 23
        List<Integer> nums2 = List.of(5, 9, 9, 2, 4, 5, 4);
        long result2 = solution.maxSum(nums2, 1, 3);
        long expected2 = 23;
        System.out.println("测试用例2结果: " + result2 + ", 期望: " + expected2);
        if (result2 == expected2) {
            System.out.println("测试用例2: 通过");
        } else {
            System.out.println("测试用例2: 失败");
        }
        
        // 测试用例3
        // 输入: nums = [1,2,1,2,6,7,5], m = 3, k = 4
        // 输出: 20
        List<Integer> nums3 = List.of(1, 2, 1, 2, 6, 7, 5);
        long result3 = solution.maxSum(nums3, 3, 4);
        long expected3 = 20;
        System.out.println("测试用例3结果: " + result3 + ", 期望: " + expected3);
        if (result3 == expected3) {
            System.out.println("测试用例3: 通过");
        } else {
            System.out.println("测试用例3: 失败");
        }
        
        // 测试用例4 - 不满足条件的情况
        // 输入: nums = [1,1,1,1], m = 2, k = 2
        // 输出: 0
        List<Integer> nums4 = List.of(1, 1, 1, 1);
        long result4 = solution.maxSum(nums4, 2, 2);
        long expected4 = 0;
        System.out.println("测试用例4结果: " + result4 + ", 期望: " + expected4);
        if (result4 == expected4) {
            System.out.println("测试用例4: 通过");
        } else {
            System.out.println("测试用例4: 失败");
        }

        // 测试用例5
        // 输入: nums = [1,2,2], m = 2, k = 2
        // 输出: 3
        List<Integer> nums5 = List.of(1, 2, 2);
        long result5 = solution.maxSum(nums5, 2, 2);
        long expected5 = 3;
        System.out.println("测试用例5结果: " + result5 + ", 期望: " + expected5);
        if (result5 == expected5) {
            System.out.println("测试用例5: 通过");
        } else {
            System.out.println("测试用例5: 失败");
        }

        // 测试用例6
        // 输入: nums = [996021492,996021492,973489433,66259330,554129007,713784351,646092981,328987029,469368828,685679486,66259330,165954500,731567840,595800464,552439059,14673238,157622945,521321042,386913607,733723177,330475939,462727944,69696035,958945846,648914457,627088742,363551051,50748590,400980660,674779765,439950964,613843311,385212079,725525766,813504429,385212079,66563232,578031878,935017574,554725813,456892672,245308625,626768145,270964388,554725813,768296675,676923124,939689721,115905765,625193590,717796816,27972217,277242430,768296675,480860474,659230631,570682291,601689140,955632265,767424000,251696645,675750691,767424000,251696645,767424000,675750691,675750691,251696645]
        // m = 9, k = 8
        // 输出: 5081057906
        List<Integer> nums6 = List.of(996021492,996021492,973489433,66259330,554129007,713784351,646092981,328987029,469368828,685679486,66259330,165954500,731567840,595800464,552439059,14673238,157622945,521321042,386913607,733723177,330475939,462727944,69696035,958945846,648914457,627088742,363551051,50748590,400980660,674779765,439950964,613843311,385212079,725525766,813504429,385212079,66563232,578031878,935017574,554725813,456892672,245308625,626768145,270964388,554725813,768296675,676923124,939689721,115905765,625193590,717796816,27972217,277242430,768296675,480860474,659230631,570682291,601689140,955632265,767424000,251696645,675750691,767424000,251696645,767424000,675750691,675750691,251696645);
        long result6 = solution.maxSum(nums6, 8, 8);
        long expected6 = 5081057906L;
        System.out.println("测试用例6结果: " + result6 + ", 期望: " + expected6);
        if (result6 == expected6) {
            System.out.println("测试用例6: 通过");
        } else {
            System.out.println("测试用例6: 失败");
        }
    }
}