package com.simon.tdd.trie;

/**
 * LeetCode 211. 添加与搜索单词 - 数据结构设计
 * <p>设计一个支持以下两种操作的数据结构：
 * <p>1. void addWord(String word) - 添加一个单词到数据结构中
 * <p>2. boolean search(String word) - 搜索单词，支持'.'通配符匹配任意字符
 * <p>实现思路：使用Trie（前缀树）数据结构，并扩展搜索功能以支持通配符
 * 
 * <p>时间复杂度分析：
 * <p>1. addWord(word):
 *    - 时间复杂度：O(m)，其中m是单词长度
 *    - 空间复杂度：O(m)，最坏情况下需要创建m个新节点
 * 
 * <p>2. search(word):
 *    - 无通配符时：O(m)，其中m是单词长度
 *    - 有通配符时：最坏情况O(26^m)，其中m是单词长度
 *    - 空间复杂度：O(m)，递归调用栈的深度
 * 
 * <p>总体空间复杂度：O(N*L)，其中N是单词数量，L是平均单词长度
 * 
 * @author Simon
 * @version 1.0
 */

/**
 * TrieNode类 - Trie树的节点实现
 * <p>每个节点包含26个子节点（对应小写字母a-z）和一个标记位表示是否为单词结尾
 */
class TrieNode {
    /** 子节点数组，每个位置对应一个小写字母 */
    private final TrieNode[] children;
    
    /** 标记当前节点是否为单词结尾 */
    private boolean isEnd;
    
    /**
     * 构造函数，初始化Trie节点
     */
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
    
    /**
     * 获取指定字符对应的子节点
     * 
     * @param ch 要获取的字符
     * @return 对应的子节点，如果不存在返回null
     */
    public TrieNode get(char ch) {
        return children[ch - 'a'];
    }
    
    /**
     * 设置指定字符的子节点
     * 
     * @param ch 要设置的字符
     * @param node 对应的子节点
     */
    public void put(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }
    
    /**
     * 检查是否为单词结尾
     * 
     * @return 如果是单词结尾返回true，否则返回false
     */
    public boolean isEnd() {
        return isEnd;
    }
    
    /**
     * 设置当前节点为单词结尾
     */
    public void setEnd() {
        this.isEnd = true;
    }
    
    /**
     * 获取所有子节点数组
     * 
     * @return 子节点数组
     */
    public TrieNode[] getChildren() {
        return children;
    }
}

/**
 * WordDictionary类 - 实现添加和搜索单词的功能
 * <p>使用Trie树存储单词，并支持通配符搜索
 * <p>遵循Clean Code原则:
 * <p>1. 单一职责原则 - 每个方法只做一件事
 * <p>2. 命名清晰 - 方法和变量名表明其用途
 * <p>3. 注释完整 - JavaDoc风格的方法说明
 * <p>4. 错误处理 - 防御性编程，处理边界情况
 * <p>5. 代码组织 - 相关功能分组，逻辑清晰
 */
class WordDictionary {
    /** Trie树的根节点 */
    private final TrieNode root;
    
    /** 通配符字符 */
    private static final char WILDCARD = '.';

    /**
     * 构造函数，初始化WordDictionary
     * <p>创建一个空的Trie树根节点
     */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /**
     * 添加单词到字典中
     * <p>时间复杂度：O(m)，其中m是单词长度
     * <p>如果单词为null或空，仍然可以正确处理
     * 
     * @param word 要添加的单词
     */
    public void addWord(String word) {
        if (word == null) {
            return;
        }
        
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.get(ch) == null) {
                current.put(ch, new TrieNode());
            }
            current = current.get(ch);
        }
        current.setEnd();
    }
    
    /**
     * 搜索单词是否在字典中
     * <p>支持'.'作为通配符匹配任意字符
     * <p>时间复杂度：
     * <p>- 最好情况：O(m)，当没有通配符时
     * <p>- 最坏情况：O(26^m)，当全是通配符时
     * 
     * @param word 要搜索的单词
     * @return 如果单词存在返回true，否则返回false
     */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        return searchInNode(word, 0, root);
    }
    
    /**
     * 递归搜索单词的辅助方法
     * <p>使用深度优先搜索策略处理通配符
     * 
     * @param word 要搜索的单词
     * @param index 当前处理的字符索引
     * @param node 当前Trie节点
     * @return 如果从当前节点开始能找到单词的剩余部分返回true，否则返回false
     */
    private boolean searchInNode(String word, int index, TrieNode node) {
        // 基本情况：已处理完所有字符
        if (index == word.length()) {
            return node.isEnd();
        }
        
        char currentChar = word.charAt(index);
        
        // 如果是通配符，需要尝试所有可能的子节点
        if (currentChar == WILDCARD) {
            return searchWithWildcard(word, index, node);
        } 
        // 普通字符，直接检查对应子节点
        return searchWithExactChar(word, index, node, currentChar);
    }
    
    /**
     * 处理通配符的搜索逻辑
     * <p>尝试当前节点的所有非空子节点
     * 
     * @param word 要搜索的单词
     * @param index 当前处理的字符索引
     * @param node 当前Trie节点
     * @return 如果找到匹配返回true，否则返回false
     */
    private boolean searchWithWildcard(String word, int index, TrieNode node) {
        for (TrieNode child : node.getChildren()) {
            if (child != null && searchInNode(word, index + 1, child)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 处理精确字符的搜索逻辑
     * <p>检查特定字符的子节点是否存在
     * 
     * @param word 要搜索的单词
     * @param index 当前处理的字符索引
     * @param node 当前Trie节点
     * @param ch 当前要匹配的字符
     * @return 如果找到匹配返回true，否则返回false
     */
    private boolean searchWithExactChar(String word, int index, TrieNode node, char ch) {
        TrieNode nextNode = node.get(ch);
        return nextNode != null && searchInNode(word, index + 1, nextNode);
    }
}

/**
 * 主测试类 - 使用TDD方式验证WordDictionary的实现
 */
/**
 * 时间复杂度分析详解
 * <p>
 * 1. addWord(word) 方法:
 * <p>   - 步骤1: 从根节点开始遍历单词的每个字符，时间复杂度O(m)
 * <p>   - 步骤2: 对每个字符，检查子节点是否存在，不存在则创建，时间复杂度O(1)
 * <p>   - 步骤3: 移动到下一个节点，时间复杂度O(1)
 * <p>   - 步骤4: 标记单词结尾，时间复杂度O(1)
 * <p>   - 总时间复杂度: O(m)，其中m是单词长度
 * <p>   - 证明: 每个字符只处理一次，每次处理的操作都是常数时间
 * 
 * <p>2. search(word) 方法:
 * <p>   - 无通配符情况:
 * <p>     * 步骤1: 从根节点开始遍历单词的每个字符，时间复杂度O(m)
 * <p>     * 步骤2: 对每个字符，检查子节点是否存在，时间复杂度O(1)
 * <p>     * 步骤3: 移动到下一个节点，时间复杂度O(1)
 * <p>     * 步骤4: 检查是否为单词结尾，时间复杂度O(1)
 * <p>     * 总时间复杂度: O(m)，其中m是单词长度
 * 
 * <p>   - 有通配符情况:
 * <p>     * 最坏情况: 所有字符都是通配符'.'
 * <p>     * 步骤1: 对每个通配符，需要尝试所有26个可能的字母
 * <p>     * 步骤2: 如果有k个通配符，时间复杂度为O(26^k)
 * <p>     * 步骤3: 最坏情况下k=m，所有字符都是通配符
 * <p>     * 总时间复杂度: O(26^m)，其中m是单词长度
 * <p>     * 证明: 每个通配符位置都需要尝试26个可能的字符，形成一个26叉树的搜索
 * 
 * <p>3. 空间复杂度分析:
 * <p>   - addWord方法: O(m)，最坏情况下需要创建m个新节点
 * <p>   - search方法: O(m)，递归调用栈的最大深度为m
 * <p>   - 总体存储空间: O(N*L)，其中N是单词数量，L是平均单词长度
 * <p>   - 证明: 每个单词最多贡献L个节点，共有N个单词
 */
public class DesignAddAndSearchWordsDataStructureTDD {
    /**
     * 测试用例类 - 遵循TDD模式
     */
    private static class TestCase {
        String name;
        Runnable testFunction;
        
        TestCase(String name, Runnable testFunction) {
            this.name = name;
            this.testFunction = testFunction;
        }
        
        void run() {
            System.out.println("运行测试: " + name);
            testFunction.run();
            System.out.println();
        }
    }
    
    /**
     * 主方法，执行所有测试用例
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("=== WordDictionary 测试 ===\n");
        
        // 定义所有测试用例
        TestCase[] testCases = new TestCase[] {
            new TestCase("基本添加和搜索功能", DesignAddAndSearchWordsDataStructureTDD::testBasicOperations),
            new TestCase("通配符搜索功能", DesignAddAndSearchWordsDataStructureTDD::testWildcardSearch),
            new TestCase("边界情况测试", DesignAddAndSearchWordsDataStructureTDD::testEdgeCases),
            new TestCase("复杂模式测试", DesignAddAndSearchWordsDataStructureTDD::testComplexPatterns),
            new TestCase("可视化Trie结构", DesignAddAndSearchWordsDataStructureTDD::visualizeTrieStructure)
        };
        
        // 运行所有测试用例
        for (TestCase testCase : testCases) {
            testCase.run();
        }
        
        System.out.println("=== 所有测试完成 ===");
    }
    
    /**
     * 测试基本的添加和搜索功能
     */
    private static void testBasicOperations() {
        WordDictionary wordDict = new WordDictionary();
        
        // 添加单词
        wordDict.addWord("bad");
        wordDict.addWord("dad");
        wordDict.addWord("mad");
        
        System.out.println("添加单词: bad, dad, mad");
        
        // 测试搜索存在的单词
        boolean result1 = wordDict.search("bad");
        System.out.println("搜索 'bad': " + (result1 ? "PASS" : "FAIL"));
        
        // 测试搜索不存在的单词
        boolean result2 = wordDict.search("pad");
        System.out.println("搜索 'pad' (不存在): " + (!result2 ? "PASS" : "FAIL"));
        
        // 测试搜索部分匹配的单词
        boolean result3 = wordDict.search("ba");
        System.out.println("搜索 'ba' (部分匹配): " + (!result3 ? "PASS" : "FAIL"));
    }
    
    /**
     * 测试通配符搜索功能
     */
    private static void testWildcardSearch() {
        WordDictionary wordDict = new WordDictionary();
        
        // 添加单词
        wordDict.addWord("bad");
        wordDict.addWord("dad");
        wordDict.addWord("mad");
        
        System.out.println("添加单词: bad, dad, mad");
        
        // 测试单个通配符
        boolean result1 = wordDict.search(".ad");
        System.out.println("搜索 '.ad': " + (result1 ? "PASS" : "FAIL"));
        
        // 测试多个通配符
        boolean result2 = wordDict.search("b..");
        System.out.println("搜索 'b..': " + (result2 ? "PASS" : "FAIL"));
        
        // 测试全通配符
        boolean result3 = wordDict.search("...");
        System.out.println("搜索 '...': " + (result3 ? "PASS" : "FAIL"));
        
        // 测试不匹配的通配符模式
        boolean result4 = wordDict.search("...."); // 长度不匹配
        System.out.println("搜索 '....' (长度不匹配): " + (!result4 ? "PASS" : "FAIL"));
        
        // 测试混合通配符
        boolean result5 = wordDict.search("b.d");
        System.out.println("搜索 'b.d': " + (result5 ? "PASS" : "FAIL"));
    }
    
    /**
     * 测试边界情况
     */
    private static void testEdgeCases() {
        WordDictionary wordDict = new WordDictionary();
        
        // 测试空字符串
        wordDict.addWord("");
        boolean result1 = wordDict.search("");
        System.out.println("添加并搜索空字符串: " + (result1 ? "PASS" : "FAIL"));
        
        // 测试单个字符
        wordDict.addWord("a");
        boolean result2 = wordDict.search("a");
        System.out.println("添加并搜索单个字符 'a': " + (result2 ? "PASS" : "FAIL"));
        
        // 测试单个通配符
        boolean result3 = wordDict.search(".");
        System.out.println("搜索单个通配符 '.': " + (result3 ? "PASS" : "FAIL"));
        
        // 测试长单词
        String longWord = "abcdefghijklmnopqrstuvwxyz";
        wordDict.addWord(longWord);
        boolean result4 = wordDict.search(longWord);
        System.out.println("添加并搜索长单词 (26个字符): " + (result4 ? "PASS" : "FAIL"));
        
        // 测试长单词通配符
        String longPattern = "abcdefghijklm............";
        boolean result5 = wordDict.search(longPattern);
        System.out.println("搜索长单词通配符模式: " + (result5 ? "PASS" : "FAIL"));
    }
    
    /**
     * 测试复杂的搜索模式
     */
    private static void testComplexPatterns() {
        WordDictionary wordDict = new WordDictionary();
        
        // 添加一些单词
        String[] words = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        for (String word : words) {
            wordDict.addWord(word);
        }
        
        System.out.println("添加单词: apple, banana, cherry, date, elderberry, fig, grape");
        
        // 测试各种复杂模式
        testPattern(wordDict, "a...e", true);  // apple
        testPattern(wordDict, ".a.a.a", true); // banana
        testPattern(wordDict, "c....y", true); // cherry
        testPattern(wordDict, "d..e", true);   // date
        testPattern(wordDict, "z....", false); // 不存在
        testPattern(wordDict, ".......", false); // 长度为7，但没有匹配
        testPattern(wordDict, "e.d.r.e.r.", true); // elderberry
        testPattern(wordDict, ".i.", true);    // fig
        testPattern(wordDict, "g...e", true);  // grape
    }
    
    /**
     * 辅助方法：测试特定模式并打印结果
     */
    private static void testPattern(WordDictionary wordDict, String pattern, boolean expected) {
        boolean result = wordDict.search(pattern);
        System.out.println("搜索 '" + pattern + "': " + 
                          (result == expected ? "PASS" : "FAIL") + 
                          " (结果: " + result + ", 期望: " + expected + ")");
    }
    
    /**
     * 可视化Trie结构
     * <p>使用ASCII字符画展示Trie树的结构和单词添加过程
     */
    private static void visualizeTrieStructure() {
        System.out.println("Trie结构可视化 (ASCII):");
        
        // 创建一个新的WordDictionary
        WordDictionary wordDict = new WordDictionary();
        
        // 逐步添加单词并展示结构
        System.out.println("\n1. 初始空Trie:");
        System.out.println("root");
        
        // 添加第一个单词 "bad"
        wordDict.addWord("bad");
        System.out.println("\n2. 添加 'bad' 后:");
        System.out.println("root");
        System.out.println("└── b");
        System.out.println("    └── a");
        System.out.println("        └── d (*)");
        
        // 添加第二个单词 "dad"
        wordDict.addWord("dad");
        System.out.println("\n3. 添加 'dad' 后:");
        System.out.println("root");
        System.out.println("├── b");
        System.out.println("│   └── a");
        System.out.println("│       └── d (*)");
        System.out.println("└── d");
        System.out.println("    └── a");
        System.out.println("        └── d (*)");
        
        // 添加第三个单词 "mad"
        wordDict.addWord("mad");
        System.out.println("\n4. 添加 'mad' 后:");
        System.out.println("root");
        System.out.println("├── b");
        System.out.println("│   └── a");
        System.out.println("│       └── d (*)");
        System.out.println("├── d");
        System.out.println("│   └── a");
        System.out.println("│       └── d (*)");
        System.out.println("└── m");
        System.out.println("    └── a");
        System.out.println("        └── d (*)");
        
        // 展示通配符搜索过程
        System.out.println("\n5. 搜索 '.ad' 的过程:");
        System.out.println("root");
        System.out.println("├── b ──┐");
        System.out.println("│   └── a │");
        System.out.println("│       └── d (*) ── 匹配!");
        System.out.println("├── d ──┤");
        System.out.println("│   └── a │");
        System.out.println("│       └── d (*) ── 匹配!");
        System.out.println("└── m ──┘");
        System.out.println("    └── a");
        System.out.println("        └── d (*) ── 匹配!");
        System.out.println("\n通配符 '.' 会尝试所有可能的子节点 (b,d,m)，找到所有匹配路径");
    }
}
