package com.simon.tdd.trie;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 648. 单词替换（Replace Words）。
 * <p>给定一个字典（roots），将句子中的每个单词用该字典中最短的“前缀根”替换；若不存在匹配前缀，则保留原词。
 * <p>算法使用 Trie（前缀树）高效匹配最短前缀根，整体时间复杂度线性于输入大小。
 */
public class ReplaceWordsTDD {

    /**
     * 将句子中的每个单词替换为字典中匹配的最短根（若存在）。
     * <p>步骤：
     * <p>1）构建字典的 Trie；
     * <p>2）逐词扫描，沿 Trie 寻找最短“终止节点”（根），找到则替换，否则保留原词。
     *
     * @param dictionary 字典根列表；允许为 null（视作空）
     * @param sentence 输入句子（空格分隔的单词）；允许为 null（返回空字符串）
     * @return 替换后的句子
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        if (sentence == null) {
            return "";
        }
        if (sentence.isEmpty()) {
            return "";
        }

        List<String> roots = (dictionary == null) ? Collections.emptyList() : dictionary;
        TrieNode trieRoot = buildTrie(roots);

        String[] words = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String shortestRoot = findShortestRoot(trieRoot, word);
            result.append(shortestRoot == null ? word : shortestRoot);
            if (i < words.length - 1) {
                result.append(' ');
            }
        }
        return result.toString();
    }

    /**
     * 构建字典的 Trie。
     * <p>忽略空白和空根。
     *
     * @param roots 字典根列表
     * @return Trie 的根节点
     */
    private TrieNode buildTrie(List<String> roots) {
        TrieNode root = new TrieNode();
        for (String r : roots) {
            if (r == null) {
                continue;
            }
            String word = r.trim();
            if (word.isEmpty()) {
                continue;
            }
            insert(root, word);
        }
        return root;
    }

    /**
     * 在 Trie 中插入一个根词。
     *
     * @param root Trie 根节点
     * @param word 要插入的根词
     */
    private void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isRoot = true;
    }

    /**
     * 查找给定单词在 Trie 中的最短根前缀；若不存在则返回 null。
     *
     * @param root Trie 根节点
     * @param word 待查找的单词
     * @return 最短根或 null
     */
    private String findShortestRoot(TrieNode root, String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();
        for (char c : word.toCharArray()) {
            TrieNode next = node.children.get(c);
            if (next == null) {
                return null;
            }
            prefix.append(c);
            if (next.isRoot) {
                return prefix.toString();
            }
            node = next;
        }
        return node.isRoot ? prefix.toString() : null;
    }

    /**
     * Trie（前缀树）节点定义。
     * <p>使用 Map 存子节点，支持通用字符集；标记 isRoot 表示该节点为某根词终止。
     */
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isRoot = false;
    }

    /**
     * 使用 main 方法进行 TDD 风格的测试。打印 PASS/FAIL。
     * <p>不引入 JUnit，便于在任意环境下直接运行。
     *
     * @param args 命令行参数（不使用）
     */
    public static void main(String[] args) {
        ReplaceWordsTDD solver = new ReplaceWordsTDD();

        runTest(solver, "LeetCode示例", Arrays.asList("cat", "bat", "rat"),
                "the cattle was rattled by the battery",
                "the cat was rat by the bat");

        runTest(solver, "选择最短根", Arrays.asList("a", "aa", "aaa", "aaaa"),
                "aadsfasf aa aaaa abcd",
                "a aa a abcd");

        runTest(solver, "无匹配前缀", Collections.singletonList("root"),
                "nomatch words",
                "nomatch words");

        runTest(solver, "空字典原样返回", Collections.emptyList(),
                "some words here",
                "some words here");

        runTest(solver, "空句子返回空", Collections.singletonList("a"),
                "",
                "");

        runTest(solver, "null句子返回空", Collections.singletonList("a"),
                null,
                "");
    }

    /**
     * 简易测试运行器：调用题解并打印 PASS/FAIL。
     *
     * @param solver 题解实例
     * @param name 测试名称
     * @param dict 字典根
     * @param sentence 句子
     * @param expected 期望输出
     */
    private static void runTest(ReplaceWordsTDD solver, String name, List<String> dict, String sentence, String expected) {
        String actual = solver.replaceWords(dict, sentence);
        boolean pass = Objects.equals(expected, actual);
        if (pass) {
            System.out.println("[" + name + "] PASS");
        } else {
            System.out.println("[" + name + "] FAIL => expected=\"" + expected + "\" actual=\"" + actual + "\"");
        }
    }
}
