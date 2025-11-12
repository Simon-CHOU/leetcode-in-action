package com.simon.hot100;

import java.util.*;

/**
 * 207. 课程表 - 拓扑排序解法
 * <p>
 * 核心思路：课程依赖关系构成有向图，能完成所有课程当且仅当图中无环
 * 使用BFS拓扑排序检测有向无环图(DAG)
 * <p>
 * 时间复杂度：O(V + E)，其中V是课程数，E是依赖关系数
 * 空间复杂度：O(V + E)
 */
public class CourseScheduleTDD {
    
    /**
     * 判断是否能完成所有课程
     * @param numCourses 总课程数
     * @param prerequisites 课程依赖关系数组，prerequisites[i] = [ai, bi] 表示课程ai依赖于课程bi
     * @return 如果能完成所有课程返回true，否则返回false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 构建邻接表和入度数组
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        // 初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 构建图：计算每个课程的入度，建立邻接关系
        for (int[] prereq : prerequisites) {
            int course = prereq[0];  // 依赖的课程
            int prereqCourse = prereq[1];  // 被依赖的课程
            adj.get(prereqCourse).add(course);  // prereqCourse -> course
            inDegree[course]++;  // course的入度+1
        }
        
        // 2. 将所有入度为0的课程加入队列（这些课程没有先修要求）
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 3. BFS拓扑排序
        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();  // 当前可以学习的课程
            completedCourses++;  // 完成课程计数
            
            // 减少所有依赖当前课程的入度
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                // 如果邻居课程入度变为0，加入队列
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // 4. 判断是否能完成所有课程
        return completedCourses == numCourses;
    }
    
    /**
     * 测试方法 - TDD模式
     * 使用main方法进行测试，不依赖JUnit
     */
    public static void main(String[] args) {
        CourseScheduleTDD solution = new CourseScheduleTDD();
        
        // 测试用例1：无环情况
        testCase(solution, "测试用例1：无环情况", 4, 
                new int[][]{{1,0}, {2,1}, {3,2}}, true);
        
        // 测试用例2：有环情况
        testCase(solution, "测试用例2：有环情况", 2, 
                new int[][]{{1,0}, {0,1}}, false);
        
        // 测试用例3：空依赖
        testCase(solution, "测试用例3：空依赖", 3, 
                new int[][]{}, true);
        
        // 测试用例4：单课程无依赖
        testCase(solution, "测试用例4：单课程无依赖", 1, 
                new int[][]{}, true);
        
        // 测试用例5：复杂依赖关系
        testCase(solution, "测试用例5：复杂依赖关系", 6, 
                new int[][]{{1,0}, {2,1}, {3,2}, {4,3}, {5,4}}, true);
    }
    
    /**
     * 执行单个测试用例
     * @param solution 解决方案实例
     * @param testName 测试名称
     * @param numCourses 课程数
     * @param prerequisites 依赖关系
     * @param expected 期望结果
     */
    private static void testCase(CourseScheduleTDD solution, String testName, 
                               int numCourses, int[][] prerequisites, boolean expected) {
        boolean result = solution.canFinish(numCourses, prerequisites);
        String status = result == expected ? "PASS" : "FAIL";
        
        System.out.println(testName + " - " + status);
        System.out.println("  输入：numCourses=" + numCourses + ", prerequisites=" + Arrays.deepToString(prerequisites));
        System.out.println("  期望：" + expected + ", 实际：" + result);
        
        if (result != expected) {
            System.out.println("  ❌ 测试失败！");
        } else {
            System.out.println("  ✅ 测试通过");
        }
        System.out.println();
    }
}
