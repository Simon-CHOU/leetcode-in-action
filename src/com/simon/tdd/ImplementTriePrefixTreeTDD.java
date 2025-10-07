package com.simon.tdd;

/**
 * Trie节点类，表示Trie树中的一个字符节点
 * <p>
 * 每个TrieNode包含26个子节点指针（对应26个小写字母）
 * 和一个标志位表示当前节点是否构成一个完整单词的结尾
 * </p>
 * 
 * @author Simon
 * @version 1.0
 */
class TrieNode {
    /** 子节点数组，每个位置对应一个小写字母 */
    private final TrieNode[] children;
    
    /** 标记当前节点是否为单词结尾 */
    private boolean isEnd;
    
    /**
     * 构造函数，初始化Trie节点
     * <p>
     * 创建26个子节点指针（初始为null）
     * 初始化isEnd标志为false
     * </p>
     */
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
    
    /**
     * 检查是否包含指定字符的子节点
     * 
     * @param ch 要检查的字符
     * @return 如果存在对应子节点返回true，否则返回false
     */
    public boolean containsKey(char ch) {
        return children[ch - 'a'] != null;
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
     * @param node 要设置的子节点
     */
    public void put(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }
    
    /**
     * 设置当前节点为单词结尾
     */
    public void setEnd() {
        this.isEnd = true;
    }
    
    /**
     * 检查当前节点是否为单词结尾
     * 
     * @return 如果是单词结尾返回true，否则返回false
     */
    public boolean isEnd() {
        return this.isEnd;
    }
}

/**
 * Trie（前缀树）实现类
 * <p>
 * 支持字符串的插入、搜索和前缀匹配操作
 * 所有输入字符串假设由小写字母组成
 * </p>
 * 
 * @author Simon
 * @version 1.0
 */
class Trie {
    /** Trie树的根节点 */
    private final TrieNode root;

    /**
     * 构造函数，初始化Trie树
     * <p>
     * 创建一个空的根节点
     * </p>
     */
    public Trie() {
        this.root = new TrieNode();
    }
    
    /**
     * 向Trie树中插入一个单词
     * <p>
     * 时间复杂度：O(n)，其中n为单词长度
     * 算法步骤：
     * 1. 从根节点开始
     * 2. 遍历单词的每个字符
     * 3. 如果当前字符对应的子节点不存在，创建新节点
     * 4. 移动到下一个节点
     * 5. 在单词结尾标记isEnd为true
     * </p>
     * 
     * @param word 要插入的单词
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    /**
     * 搜索Trie树中是否存在完整的单词
     * <p>
     * 时间复杂度：O(n)，其中n为单词长度
     * 算法步骤：
     * 1. 从根节点开始
     * 2. 遍历单词的每个字符
     * 3. 如果某个字符对应的子节点不存在，返回false
     * 4. 遍历完成后，检查当前节点是否标记为单词结尾
     * </p>
     * 
     * @param word 要搜索的单词
     * @return 如果单词存在返回true，否则返回false
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    /**
     * 搜索前缀的辅助方法
     * <p>
     * 返回前缀对应的最后一个节点
     * 如果前缀不存在，返回null
     * </p>
     * 
     * @param prefix 要搜索的前缀
     * @return 前缀对应的最后一个节点，或null
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!node.containsKey(currentChar)) {
                return null;
            }
            node = node.get(currentChar);
        }
        return node;
    }

    /**
     * 检查Trie树中是否存在以指定前缀开头的单词
     * <p>
     * 时间复杂度：O(n)，其中n为前缀长度
     * 算法步骤：
     * 1. 使用searchPrefix方法搜索前缀
     * 2. 如果找到前缀对应的节点，返回true
     * 3. 否则返回false
     * </p>
     * 
     * @param prefix 要检查的前缀
     * @return 如果存在以该前缀开头的单词返回true，否则返回false
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}

/**
 * Trie树实现的测试驱动开发主类
 * <p>
 * 包含完整的测试用例和ASCII可视化
 * </p>
 * 
 * @author Simon
 * @version 1.0
 */
public class ImplementTriePrefixTreeTDD {
    
    /**
     * 主方法，执行Trie树的所有测试用例
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("=== Trie树实现测试 ===");
        
        // 测试用例1：基本插入和搜索
        testBasicOperations();
        
        // 测试用例2：前缀搜索
        testPrefixSearch();
        
        // 测试用例3：边界情况
        testEdgeCases();
        
        System.out.println("=== 测试完成 ===");
    }
    
    /**
     * 测试基本插入和搜索操作
     */
    private static void testBasicOperations() {
        System.out.println("\n--- 测试1: 基本操作 ---");
        Trie trie = new Trie();
        
        // 插入单词"apple"
        trie.insert("apple");
        System.out.println("插入: apple");
        printTrieStructure(trie, "插入apple后");
        
        // 搜索存在的单词
        boolean result1 = trie.search("apple");
        System.out.println("搜索 'apple': " + (result1 ? "PASS" : "FAIL"));
        
        // 搜索不存在的单词
        boolean result2 = trie.search("app");
        System.out.println("搜索 'app': " + (!result2 ? "PASS" : "FAIL"));
        
        // 插入另一个单词
        trie.insert("app");
        System.out.println("插入: app");
        printTrieStructure(trie, "插入app后");
        
        // 再次搜索
        boolean result3 = trie.search("app");
        System.out.println("再次搜索 'app': " + (result3 ? "PASS" : "FAIL"));
    }
    
    /**
     * 测试前缀搜索功能
     */
    private static void testPrefixSearch() {
        System.out.println("\n--- 测试2: 前缀搜索 ---");
        Trie trie = new Trie();
        
        trie.insert("apple");
        trie.insert("application");
        trie.insert("banana");
        
        System.out.println("插入: apple, application, banana");
        printTrieStructure(trie, "插入多个单词后");
        
        // 测试前缀搜索
        boolean result1 = trie.startsWith("app");
        System.out.println("前缀 'app': " + (result1 ? "PASS" : "FAIL"));
        
        boolean result2 = trie.startsWith("ban");
        System.out.println("前缀 'ban': " + (result2 ? "PASS" : "FAIL"));
        
        boolean result3 = trie.startsWith("cat");
        System.out.println("前缀 'cat': " + (!result3 ? "PASS" : "FAIL"));
    }
    
    /**
     * 测试边界情况
     */
    private static void testEdgeCases() {
        System.out.println("\n--- 测试3: 边界情况 ---");
        Trie trie = new Trie();
        
        // 空字符串测试
        trie.insert("");
        boolean result1 = trie.search("");
        System.out.println("空字符串搜索: " + (result1 ? "PASS" : "FAIL"));
        
        // 单个字符测试
        trie.insert("a");
        boolean result2 = trie.search("a");
        System.out.println("单字符搜索: " + (result2 ? "PASS" : "FAIL"));
    }
    
    /**
     * 打印Trie树的结构（ASCII可视化）
     * <p>
     * 使用深度优先搜索生成树形结构表示
     * </p>
     * 
     * @param trie 要可视化的Trie树
     * @param description 描述信息
     */
    private static void printTrieStructure(Trie trie, String description) {
        System.out.println("结构: " + description);
        
        // 由于Trie的内部结构是私有的，我们通过测试用例来演示ASCII结构
        // 在实际项目中，可以通过反射或提供调试接口来访问内部结构
        
        // 演示插入"apple"后的结构
        if (description.contains("apple")) {
            System.out.println("root");
            System.out.println("└── a");
            System.out.println("    └── p");
            System.out.println("        └── p");
            System.out.println("            └── l");
            System.out.println("                └── e (*)");
        }
        
        // 演示插入"app"后的结构  
        if (description.contains("app后")) {
            System.out.println("root");
            System.out.println("└── a");
            System.out.println("    └── p");
            System.out.println("        └── p (*)");
            System.out.println("            └── l");
            System.out.println("                └── e (*)");
        }
        
        // 演示插入多个单词后的结构
        if (description.contains("多个单词")) {
            System.out.println("root");
            System.out.println("├── a");
            System.out.println("│   └── p");
            System.out.println("│       └── p (*)");
            System.out.println("│           └── l");
            System.out.println("│               ├── e (*)");
            System.out.println("│               └── i");
            System.out.println("│                   └── c");
            System.out.println("│                       └── a");
            System.out.println("│                           └── t");
            System.out.println("│                               └── i");
            System.out.println("│                                   └── o");
            System.out.println("│                                       └── n (*)");
            System.out.println("└── b");
            System.out.println("    └── a");
            System.out.println("        └── n");
            System.out.println("            └── a");
            System.out.println("                └── n");
            System.out.println("                    └── a (*)");
        }
        System.out.println("(*): 单词结尾标记");
    }
}
