package com.simon.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串<p>
 * 
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。<p>
 * 
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。<p>
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。<p>
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上。<p>
 * 
 * 算法思路：<p>
 * 使用两个HashMap分别记录s到t和t到s的字符映射关系，确保映射是一一对应的。<p>
 * 遍历两个字符串，检查每个位置上的字符是否符合已建立的映射关系。<p>
 * 
 * 算法演示：<p>
 * s = "egg", t = "add"<p>
 * <p>
 * 初始状态: s->t: {}, t->s: {}<p>
 * 第1步(i=0): s='e', t='a' → 建立映射 e->a, a->e → s->t: {e=a}, t->s: {a=e}<p>
 * 第2步(i=1): s='g', t='d' → 建立映射 g->d, d->g → s->t: {e=a, g=d}, t->s: {a=e, d=g}<p>
 * 第3步(i=2): s='g', t='d' → 检查映射 g->d, d->g 一致 → 符合同构<p>
 */
public class IsomorphicStringsTDD {
    
    /**
     * 判断两个字符串是否是同构的
     * 
     * @param s 第一个字符串
     * @param t 第二个字符串
     * @return 如果两个字符串同构返回true，否则返回false
     */
    public boolean isIsomorphic(String s, String t) {
        // 边界条件检查：长度不同肯定不同构
        if (s.length() != t.length()) {
            return false;
        }
        
        // 创建两个映射表，分别记录s到t和t到s的字符映射关系
        Map<Character, Character> sToTMap = new HashMap<>();
        Map<Character, Character> tToSMap = new HashMap<>();
        
        // 遍历字符串的每个位置
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            
            // 检查s到t的映射
            if (sToTMap.containsKey(charS)) {
                // 如果s中的字符已有映射，但与当前t中的字符不一致，则不同构
                if (sToTMap.get(charS) != charT) {
                    return false;
                }
            } else {
                // 建立新的映射关系
                sToTMap.put(charS, charT);
            }
            
            // 检查t到s的映射
            if (tToSMap.containsKey(charT)) {
                // 如果t中的字符已有映射，但与当前s中的字符不一致，则不同构
                if (tToSMap.get(charT) != charS) {
                    return false;
                }
            } else {
                // 建立新的映射关系
                tToSMap.put(charT, charS);
            }
        }
        
        // 所有字符都满足映射关系，是同构字符串
        return true;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        IsomorphicStringsTDD solution = new IsomorphicStringsTDD();
        
        // 测试用例1: s = "egg", t = "add" → 期望输出: true
        boolean result1 = solution.isIsomorphic("egg", "add");
        System.out.println("测试用例1 - 输入: s=\"egg\", t=\"add\" 结果: " + result1 + " (期望: true) " + 
                          (result1 == true ? "PASS" : "FAIL"));
        
        // 测试用例2: s = "foo", t = "bar" → 期望输出: false
        boolean result2 = solution.isIsomorphic("foo", "bar");
        System.out.println("测试用例2 - 输入: s=\"foo\", t=\"bar\" 结果: " + result2 + " (期望: false) " + 
                          (result2 == false ? "PASS" : "FAIL"));
        
        // 测试用例3: s = "paper", t = "title" → 期望输出: true
        boolean result3 = solution.isIsomorphic("paper", "title");
        System.out.println("测试用例3 - 输入: s=\"paper\", t=\"title\" 结果: " + result3 + " (期望: true) " + 
                          (result3 == true ? "PASS" : "FAIL"));
        
        // 测试用例4: s = "ab", t = "aa" → 期望输出: false
        boolean result4 = solution.isIsomorphic("ab", "aa");
        System.out.println("测试用例4 - 输入: s=\"ab\", t=\"aa\" 结果: " + result4 + " (期望: false) " + 
                          (result4 == false ? "PASS" : "FAIL"));
        
        // 测试用例5: s = "badc", t = "baba" → 期望输出: false
        boolean result5 = solution.isIsomorphic("badc", "baba");
        System.out.println("测试用例5 - 输入: s=\"badc\", t=\"baba\" 结果: " + result5 + " (期望: false) " + 
                          (result5 == false ? "PASS" : "FAIL"));
    }
}