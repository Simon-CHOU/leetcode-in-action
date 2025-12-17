package com.simon.hot100;

import java.util.*;

/**
 * LeetCode 399. 除法求值
 * <p>
 * 给定一组变量间的除法关系，计算查询中变量的除法结果。
 * 使用图论和BFS算法解决问题。
 */
public class EvaluateDivisionTDD {

    /**
     * 计算查询中变量的除法结果
     * <p>
     * 方法思路：
     * 1. 构建带权图存储变量间的除法关系
     * 2. 对每个查询使用BFS寻找路径
     * 3. 计算路径上所有边的权重乘积
     *
     * @param equations 变量对列表，表示除法关系
     * @param values    对应的除法结果
     * @param queries   待查询的变量对
     * @return 每个查询的结果，无法计算返回-1.0
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构建关系图：每个变量映射到其可以直接计算的其他变量和对应的值
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        
        double[] result = new double[queries.size()];
        
        // 处理每个查询
        for (int i = 0; i < queries.size(); i++) {
            String startVar = queries.get(i).get(0);
            String endVar = queries.get(i).get(1);
            
            // 如果任一变量不在关系中，返回-1.0
            if (!graph.containsKey(startVar) || !graph.containsKey(endVar)) {
                result[i] = -1.0;
                continue;
            }
            
            // 如果查询的是同一变量，返回1.0
            if (startVar.equals(endVar)) {
                result[i] = 1.0;
                continue;
            }
            
            // 使用BFS寻找从startVar到endVar的路径并计算结果
            result[i] = bfs(graph, startVar, endVar);
        }
        
        return result;
    }
    
    /**
     * 构建关系图
     * <p>
     * 对于每个除法表达式a/b=value，创建两条有向边：
     * a->b(权重为value)和b->a(权重为1/value)
     *
     * @param equations 变量对列表
     * @param values    对应的除法结果
     * @return 构建好的关系图
     */
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            double value = values[i];
            
            // 确保两个变量都在图中
            graph.putIfAbsent(var1, new HashMap<>());
            graph.putIfAbsent(var2, new HashMap<>());
            
            // 添加边：var1 -> var2 (权重为value)
            graph.get(var1).put(var2, value);
            // 添加反向边：var2 -> var1 (权重为1/value)
            graph.get(var2).put(var1, 1.0 / value);
        }
        
        return graph;
    }
    
    /**
     * 使用广度优先搜索寻找从起始变量到目标变量的路径
     * <p>
     * 算法过程：
     * 1. 从起始变量开始，使用队列进行BFS
     * 2. 记录已访问变量及其累积权重
     * 3. 找到目标变量时返回累积权重
     *
     * @param graph    关系图
     * @param startVar 起始变量
     * @param endVar   目标变量
     * @return 从起始变量到目标变量的除法结果，无法计算返回-1.0
     */
    private double bfs(Map<String, Map<String, Double>> graph, String startVar, String endVar) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Double> visited = new HashMap<>();
        
        // 从起始变量开始，初始权重为1.0
        queue.offer(startVar);
        visited.put(startVar, 1.0);
        
        while (!queue.isEmpty()) {
            String currentVar = queue.poll();
            double currentValue = visited.get(currentVar);
            
            // 找到目标变量，返回累积权重
            if (currentVar.equals(endVar)) {
                return currentValue;
            }
            
            // 遍历当前变量的所有邻居
            for (Map.Entry<String, Double> entry : graph.get(currentVar).entrySet()) {
                String nextVar = entry.getKey();
                double edgeWeight = entry.getValue();
                
                // 如果邻居变量未被访问过
                if (!visited.containsKey(nextVar)) {
                    // 计算从起始变量到邻居变量的累积权重
                    double nextValue = currentValue * edgeWeight;
                    visited.put(nextVar, nextValue);
                    queue.offer(nextVar);
                }
            }
        }
        
        // 未找到路径，返回-1.0
        return -1.0;
    }
    
    /**
     * 测试用例
     * <p>
     * 使用TDD方式测试calcEquation方法
     */
    public static void main(String[] args) {
        EvaluateDivisionTDD solution = new EvaluateDivisionTDD();
        
        // 测试用例1: 基础测试
        System.out.println("测试用例1: 基础测试");
        List<List<String>> equations1 = new ArrayList<>();
        equations1.add(Arrays.asList("a", "b"));
        equations1.add(Arrays.asList("b", "c"));
        
        double[] values1 = {2.0, 3.0};
        
        List<List<String>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList("a", "c"));
        queries1.add(Arrays.asList("b", "a"));
        queries1.add(Arrays.asList("a", "e"));
        queries1.add(Arrays.asList("a", "a"));
        queries1.add(Arrays.asList("x", "x"));
        
        double[] result1 = solution.calcEquation(equations1, values1, queries1);
        double[] expected1 = {6.0, 0.5, -1.0, 1.0, -1.0};
        System.out.println("结果: " + Arrays.toString(result1));
        System.out.println("期望: " + Arrays.toString(expected1));
        System.out.println(testResult(result1, expected1) ? "PASS" : "FAIL");
        
        // 测试用例2: 复杂关系测试
        System.out.println("测试用例2: 复杂关系测试");
        List<List<String>> equations2 = new ArrayList<>();
        equations2.add(Arrays.asList("a", "b"));
        equations2.add(Arrays.asList("b", "c"));
        equations2.add(Arrays.asList("bc", "cd"));
        
        double[] values2 = {1.5, 2.5, 5.0};
        
        List<List<String>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList("a", "c"));
        queries2.add(Arrays.asList("c", "b"));
        queries2.add(Arrays.asList("bc", "cd"));
        queries2.add(Arrays.asList("cd", "bc"));
        queries2.add(Arrays.asList("a", "e"));
        
        double[] result2 = solution.calcEquation(equations2, values2, queries2);
        double[] expected2 = {3.75, 0.4, 5.0, 0.2, -1.0};
        System.out.println("结果: " + Arrays.toString(result2));
        System.out.println("期望: " + Arrays.toString(expected2));
        System.out.println(testResult(result2, expected2) ? "PASS" : "FAIL");
        
        // 测试用例3: 自引用测试
        System.out.println("测试用例3: 自引用测试");
        List<List<String>> equations3 = new ArrayList<>();
        equations3.add(Arrays.asList("a", "a"));
        
        double[] values3 = {1.0};
        
        List<List<String>> queries3 = new ArrayList<>();
        queries3.add(Arrays.asList("a", "a"));
        
        double[] result3 = solution.calcEquation(equations3, values3, queries3);
        double[] expected3 = {1.0};
        System.out.println("结果: " + Arrays.toString(result3));
        System.out.println("期望: " + Arrays.toString(expected3));
        System.out.println(testResult(result3, expected3) ? "PASS" : "FAIL");
        
        // 测试用例4: 空输入测试
        System.out.println("测试用例4: 空输入测试");
        List<List<String>> equations4 = new ArrayList<>();
        double[] values4 = {};
        List<List<String>> queries4 = new ArrayList<>();
        
        double[] result4 = solution.calcEquation(equations4, values4, queries4);
        double[] expected4 = {};
        System.out.println("结果: " + Arrays.toString(result4));
        System.out.println("期望: " + Arrays.toString(expected4));
        System.out.println(testResult(result4, expected4) ? "PASS" : "FAIL");
    }
    
    /**
     * 比较两个double数组是否相等
     * <p>
     * 考虑到浮点数精度问题，使用近似比较
     *
     * @param result   实际结果
     * @param expected 期望结果
     * @return 如果结果相等返回true，否则返回false
     */
    private static boolean testResult(double[] result, double[] expected) {
        if (result.length != expected.length) {
            return false;
        }
        
        for (int i = 0; i < result.length; i++) {
            // 使用近似比较处理浮点数精度问题
            if (Math.abs(result[i] - expected[i]) > 1e-9) {
                return false;
            }
        }
        
        return true;
    }
}
