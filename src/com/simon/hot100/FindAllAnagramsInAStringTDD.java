package com.simon.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all start indices of p's anagrams in s using a sliding window with constant-size
 * frequency arrays and an incremental mismatch counter.
 * <p>Constraints: s, p consist of lowercase English letters.
 * <p>Approach: Maintain counts for p (need[]) and for the current window (win[]).
 * Keep an integer diff representing how many positions in [0..25] differ between win and need.
 * Initialize the first window, then slide one character at a time, updating diff in O(1).
 * <p>Time: O(n) where n = s.length(), Space: O(1) excluding output list.
 */
class FindAllAnagramsInAStringTDD {

    /**
     * Finds all start indices of p's anagrams in s.
     * <p>Returns a list of indices i such that s.substring(i, i + p.length()) is an anagram of p.
     *
     * @param s the text to search
     * @param p the pattern whose anagrams are sought
     * @return list of starting indices where anagrams of p occur in s
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null) {
            return result;
        }
        int n = s.length();
        int m = p.length();
        if (m == 0 || n < m) {
            return result;
        }

        // Frequency arrays for lowercase English letters.
        int[] need = new int[26];
        int[] win = new int[26];

        for (int i = 0; i < m; i++) {
            need[p.charAt(i) - 'a']++;
            win[s.charAt(i) - 'a']++;
        }

        // diff = number of positions where win[i] != need[i].
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (win[i] != need[i]) {
                diff++;
            }
        }

        if (diff == 0) {
            result.add(0);
        }

        // Slide the window from left to right by one position each time.
        for (int right = m; right < n; right++) {
            int left = right - m;

            // Character leaving window.
            int outIdx = s.charAt(left) - 'a';
            // Before change: was this index equal?
            boolean beforeOutEqual = (win[outIdx] == need[outIdx]);
            win[outIdx]--;
            boolean afterOutEqual = (win[outIdx] == need[outIdx]);
            diff += delta(beforeOutEqual, afterOutEqual);

            // Character entering window.
            int inIdx = s.charAt(right) - 'a';
            boolean beforeInEqual = (win[inIdx] == need[inIdx]);
            win[inIdx]++;
            boolean afterInEqual = (win[inIdx] == need[inIdx]);
            diff += delta(beforeInEqual, afterInEqual);

            // If all positions match, record start index.
            if (diff == 0) {
                result.add(left + 1);
            }
        }

        return result;
    }

    /**
     * Computes the change to diff based on equality transitions.
     * <p>If an index transitions from equal->unequal, diff increases by 1.
     * <p>If an index transitions from unequal->equal, diff decreases by 1.
     * <p>Otherwise, diff does not change.
     *
     * @param beforeEqual whether win[i] == need[i] before update
     * @param afterEqual  whether win[i] == need[i] after update
     * @return +1, -1, or 0 to adjust diff
     */
    private int delta(boolean beforeEqual, boolean afterEqual) {
        if (beforeEqual && !afterEqual) {
            return 1;
        }
        if (!beforeEqual && afterEqual) {
            return -1;
        }
        return 0;
    }

    /**
     * Test runner using a main method. No external frameworks.
     * <p>Prints PASS/FAIL with expected vs. actual results for multiple scenarios.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        FindAllAnagramsInAStringTDD solver = new FindAllAnagramsInAStringTDD();

        // Test 1: standard example
        assertEquals("Test1", listOf(0, 6), solver.findAnagrams("cbaebabacd", "abc"));

        // Test 2: multiple overlapping hits
        assertEquals("Test2", listOf(0, 1, 2), solver.findAnagrams("abab", "ab"));

        // Test 3: no matches
        assertEquals("Test3", listOf(), solver.findAnagrams("abcd", "ef"));

        // Test 4: pattern longer than text
        assertEquals("Test4", listOf(), solver.findAnagrams("a", "aa"));

        // Test 5: identical strings
        assertEquals("Test5", listOf(0), solver.findAnagrams("anagram", "anagram"));

        // Test 6: repeated chars
        assertEquals("Test6", listOf(1), solver.findAnagrams("aaab", "aab"));

        // Test 7: edge case empty pattern
        assertEquals("Test7", listOf(), solver.findAnagrams("abc", ""));

        // Test 8: null inputs
        assertEquals("Test8", listOf(), solver.findAnagrams(null, "a"));
        assertEquals("Test9", listOf(), solver.findAnagrams("abc", null));

        // Test 10: exact matches across
        assertEquals("Test10", listOf(0, 2, 3), solver.findAnagrams("baaaba", "aab"));
    }

    /**
     * Lightweight assertion: prints PASS/FAIL.
     * <p>Compares lists element-wise and prints result with context.
     *
     * @param name   test case name
     * @param expect expected list
     * @param actual actual list
     */
    private static void assertEquals(String name, List<Integer> expect, List<Integer> actual) {
        boolean pass = (expect.size() == actual.size());
        if (pass) {
            for (int i = 0; i < expect.size(); i++) {
                if (!expect.get(i).equals(actual.get(i))) {
                    pass = false;
                    break;
                }
            }
        }
        if (pass) {
            System.out.println(name + " PASS " + expect + " == " + actual);
        } else {
            System.out.println(name + " FAIL expect=" + expect + " actual=" + actual);
        }
    }

    /**
     * Helper to build a list of integers.
     * <p>Improves test readability without external dependencies.
     *
     * @param values variable number of integers
     * @return list containing given values
     */
    private static List<Integer> listOf(int... values) {
        List<Integer> list = new ArrayList<>();
        for (int v : values) {
            list.add(v);
        }
        return list;
    }
}