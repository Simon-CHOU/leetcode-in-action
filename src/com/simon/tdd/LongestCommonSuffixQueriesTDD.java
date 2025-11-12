package com.simon.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * 解答 LeetCode 3093: 最长公共后缀查询。
 * <p>核心思想：将容器字符串倒序插入 Trie（后缀即对应倒序的前缀），
 * 在每个节点维护“当前路径最佳字符串索引”（最短长度优先，索引次之）。
 * 查询时，将 query 倒序沿 Trie 走，能够走到的最深节点的最佳索引即答案；
 * 若一个字符也走不动，则返回根节点维护的“全局最优（最短）字符串索引”。
 * <p>数据结构考察：Trie（字典树），不过是针对倒序字符串以支持“后缀”匹配。
 * <p>时间复杂度：构建 O(Σ|w|)，查询 O(Σ|q|)，详见下文 main 中分析与证明。
 */
public class LongestCommonSuffixQueriesTDD {

    /** 容器字符串缓存，供比较使用 */
    private String[] container;

    /**
     * Trie 节点定义（使用通用字符映射，兼容所有字符集）。
     * <p>每个节点维护 bestIndex：沿当前路径（即某个倒序前缀）时，
     * 最佳的容器字符串索引（以长度最短优先，索引最小次之）。
     */
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        int bestIndex = -1;
    }

    /** 根节点 */
    private final Node root = new Node();

    /**
     * 计算每个查询字符串在容器中的“最长公共后缀”对应的最优索引。
     * <p>比较规则：最大化公共后缀长度；若有并列，则选择容器中长度更短者；
     * 若仍并列，则选择索引更小者。
     *
     * @param wordsContainer 容器字符串数组
     * @param wordsQuery     查询字符串数组
     * @return 对应每个查询的容器索引
     */
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        this.container = wordsContainer;
        buildReversedTrie(wordsContainer);

        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = queryReversedTrie(wordsQuery[i]);
        }
        return ans;
    }

    /**
     * 将容器字符串全部“倒序”插入 Trie，并在路径上维护 bestIndex。
     * <p>根节点也调用 updateBestIndex，以便当一个字符都无法匹配时，能返回全局最短者。
     *
     * @param words 容器字符串数组
     */
    private void buildReversedTrie(String[] words) {
        for (int i = 0; i < words.length; i++) {
            updateBestIndex(root, i);
            Node cur = root;
            String w = words[i];
            for (int p = w.length() - 1; p >= 0; p--) {
                char c = w.charAt(p);
                Node next = cur.children.get(c);
                if (next == null) {
                    next = new Node();
                    cur.children.put(c, next);
                }
                updateBestIndex(next, i);
                cur = next;
            }
        }
    }

    /**
     * 在节点上尝试用 candidateIndex 更新 bestIndex，比较规则：
     * <p>1）长度更短者优先；
     * <p>2）若长度相同，则索引更小者优先。
     *
     * @param node           待更新的节点
     * @param candidateIndex 候选索引
     */
    private void updateBestIndex(Node node, int candidateIndex) {
        if (node.bestIndex == -1) {
            node.bestIndex = candidateIndex;
            return;
        }
        int bestLen = container[node.bestIndex].length();
        int candLen = container[candidateIndex].length();
        if (candLen < bestLen || (candLen == bestLen && candidateIndex < node.bestIndex)) {
            node.bestIndex = candidateIndex;
        }
    }

    /**
     * 查询：将字符串倒序，从根开始按字符依次下沉；
     * <p>每成功走一层，都更新“最后可达节点的最佳索引”。
     * 若无法继续，立即返回当前记录的最佳索引。
     *
     * @param q 查询字符串
     * @return 最优容器索引
     */
    private int queryReversedTrie(String q) {
        Node cur = root;
        int lastBest = root.bestIndex; // 即使一个字符不匹配，也可返回全局最短者
        for (int p = q.length() - 1; p >= 0; p--) {
            char c = q.charAt(p);
            Node next = cur.children.get(c);
            if (next == null) {
                return lastBest;
            }
            cur = next;
            lastBest = cur.bestIndex;
        }
        return lastBest;
    }

    /**
     * 使用 main 作为 TDD 驱动，打印 PASS/FAIL。
     * <p>包含若干覆盖典型场景的用例：
     * <p>1）有公共后缀时，选择更长匹配；并列选更短容器；再并列选更小索引。
     * <p>2）完全无公共后缀时，返回容器中全局最短（再并列看索引）。
     */
    public static void main(String[] args) {
        LongestCommonSuffixQueriesTDD solver = new LongestCommonSuffixQueriesTDD();

        // 用例1：多字符串并列公共后缀，长度更短者优先
        String[] container1 = {"abcd", "bcd", "xbcd"};
        String[] query1 = {"zzbcd", "bcdx", "abxy"};
        int[] expect1 = {1, 1, 1};
        runCase("Case1", solver, container1, query1, expect1);

        // 用例2：更长后缀匹配应优先（"aaa" 比 "aa" 更优）
        String[] container2 = {"aa", "aaa"};
        String[] query2 = {"baaa"};
        int[] expect2 = {1};
        runCase("Case2", solver, container2, query2, expect2);

        // 用例3：无公共后缀，返回全局最短；若并列看索引
        String[] container3 = {"a", "b"};
        String[] query3 = {"xyz"};
        int[] expect3 = {0};
        runCase("Case3", solver, container3, query3, expect3);

        // 用例4：混合字符，验证 Map 兼容性
        String[] container4 = {"Alpha-1", "beta_2", "gamma#3"};
        String[] query4 = {"zzbeta_2", "XXgamma#3", "Alpha-1YY"};
        int[] expect4 = {1, 2, 0};
        runCase("Case4", solver, container4, query4, expect4);

        // 简要时间复杂度分析与证明（打印说明）
        printComplexityAnalysis();
    }

    /**
     * 运行一个 TDD 用例并打印 PASS/FAIL。
     *
     * @param name      用例名
     * @param solver    求解器实例
     * @param container 容器数组
     * @param query     查询数组
     * @param expected  期望结果
     */
    private static void runCase(String name, LongestCommonSuffixQueriesTDD solver,
                                String[] container, String[] query, int[] expected) {
        int[] actual = solver.stringIndices(container, query);
        boolean pass = equalsArray(actual, expected);
        System.out.println(name + " => " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.print("  expected: ");
            printArray(expected);
            System.out.print("  actual  : ");
            printArray(actual);
        }
    }

    /** 数组比较 */
    private static boolean equalsArray(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    /** 打印数组 */
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }

    /**
     * 打印时间复杂度分析（分步骤证明）。
     * <p>构建：对每个容器字符串 w，将其倒序逐字符插入 Trie。
     * 对 w 的每个字符，进行一次哈希映射查找/插入与常数更新 bestIndex。
     * 因此对 w 的代价为 O(|w|)。对全部容器的总代价为 O(Σ|w|)。
     * <p>查询：对每个查询 q，将其倒序逐字符从根下沉。每下沉一步做一次映射查找，
     * 若失败立即停止并返回当前 lastBest；最多执行 |q| 次查找，因此代价 O(|q|)。
     * 对全部查询的总代价为 O(Σ|q|)。
     * <p>空间：Trie 节点总数不超过 Σ|w|（每个容器字符最多引入一个新节点），
     * 因此空间复杂度为 O(Σ|w|)。
     */
    private static void printComplexityAnalysis() {
        System.out.println("Complexity => Build: O(sum|w|), Query: O(sum|q|), Space: O(sum|w|)");
    }
}
