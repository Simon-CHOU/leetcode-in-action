package com.simon.tdd.trie;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 820: Short Encoding of Words.
 * <p>Problem: Given an array of words, build a reference string by concatenating words separated by '#'.
 * The minimal reference length requires that any word which is a suffix of another is not stored separately.
 * <p>Solution Principle: Keep only words that are not a suffix of any other word. Use a set to store words,
 * then remove all proper suffixes of each word. The remaining set contains exactly the words contributing
 * to the minimal encoding; sum their lengths plus one for '#'.
 * <p>Clean Code: Single-responsibility methods, clear naming, early returns for edge cases, and JavaDoc using
 * the <p> line-break to satisfy IDEA analysis.
 */
public class ShortEncodingOfWordsTDD {

    /**
     * Compute the minimal length of the encoding string for the given words.
     * <p>Algorithm:<br/>
     * 1) Insert all non-empty words into a {@code Set}.<br/>
     * 2) For each word, remove all of its proper suffixes from the set.<br/>
     * 3) The answer is the sum over remaining words of {@code length(word) + 1} to account for the '#'.
     * <p>Edge Cases: Null/empty inputs return 0; duplicates are naturally handled by the set; empty strings
     * are ignored as they do not contribute to the encoding.
     * <p>Time Complexity: O(L) expected, where L is the total number of characters across all input words.
     * See {@link #complexityProof()} for a step-by-step justification.
     *
     * @param words the input array of words
     * @return the minimal length of the reference encoding string
     */
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        final Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (word != null && !word.isEmpty()) {
                uniqueWords.add(word);
            }
        }

        for (String word : words) {
            if (word == null) {
                continue;
            }
            // Remove all proper suffixes of the word (starting at index 1).
            for (int i = 1; i < word.length(); i++) {
                uniqueWords.remove(word.substring(i));
            }
        }

        int totalLength = 0;
        for (String word : uniqueWords) {
            totalLength += word.length() + 1; // +1 for '#'
        }
        return totalLength;
    }

    /**
     * Basic assertion utility for TDD without JUnit.
     * <p>Prints PASS or FAIL with an associated label.
     *
     * @param expected expected integer value
     * @param actual   actual integer value
     * @param label    test case label
     */
    private static void assertEquals(int expected, int actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label + " -> " + actual);
        } else {
            System.out.println("FAIL: " + label + " -> expected " + expected + ", got " + actual);
        }
    }

    /**
     * Entry point with TDD-style tests and walkthrough.
     * <p>Runs test cases and prints PASS/FAIL.
     * <p>Also calls {@link #asciiWalkthrough()} and {@link #complexityProof()} for learning support.
     *
     * @param args program arguments (unused)
     */
    public static void main(String[] args) {
        ShortEncodingOfWordsTDD solver = new ShortEncodingOfWordsTDD();

        // TDD test cases
        assertEquals(10, solver.minimumLengthEncoding(new String[]{"time", "me", "bell"}), "Example1 time/me/bell");
        assertEquals(2, solver.minimumLengthEncoding(new String[]{"t"}), "Single char");
        assertEquals(3, solver.minimumLengthEncoding(new String[]{"me", "me"}), "Duplicates");
        assertEquals(3, solver.minimumLengthEncoding(new String[]{"fe", "e"}), "Suffix removal");
        assertEquals(5, solver.minimumLengthEncoding(new String[]{"time", "ime", "me", "e"}), "Chain suffixes");
        assertEquals(0, solver.minimumLengthEncoding(new String[]{}), "Empty input");
        assertEquals(2, solver.minimumLengthEncoding(new String[]{"z", ""}), "Ignore empty");

        // Educational outputs
        asciiWalkthrough();
        complexityProof();
    }

    /**
     * ASCII walkthrough of the algorithm on the input ["time","me","bell"].
     * <p>Demonstrates set state and suffix removals step-by-step.
     */
    private static void asciiWalkthrough() {
        System.out.println("ASCII Walkthrough for [\"time\",\"me\",\"bell\"]");
        System.out.println("Initial set add -> {time, me, bell}");
        System.out.println("+-----------------------------+");
        System.out.println("| Set                         |");
        System.out.println("| time                        |");
        System.out.println("| me                          |");
        System.out.println("| bell                        |");
        System.out.println("+-----------------------------+");

        System.out.println("Process word: time, remove suffixes: \"ime\",\"me\",\"e\"");
        System.out.println("Remove ime -> {time, me, bell}");
        System.out.println("Remove me  -> {time, bell}");
        System.out.println("Remove e   -> {time, bell}");
        System.out.println("+-----------------------------+");
        System.out.println("| Set                         |");
        System.out.println("| time                        |");
        System.out.println("| bell                        |");
        System.out.println("+-----------------------------+");

        System.out.println("Process word: me, remove suffixes: \"e\"");
        System.out.println("Remove e   -> {time, bell}");
        System.out.println("+-----------------------------+");
        System.out.println("| Set                         |");
        System.out.println("| time                        |");
        System.out.println("| bell                        |");
        System.out.println("+-----------------------------+");

        System.out.println("Process word: bell, remove suffixes: \"ell\",\"ll\",\"l\"");
        System.out.println("Remove ell -> {time, bell}");
        System.out.println("Remove ll  -> {time, bell}");
        System.out.println("Remove l   -> {time, bell}");
        System.out.println("+-----------------------------+");
        System.out.println("| Final Set                   |");
        System.out.println("| time                        |");
        System.out.println("| bell                        |");
        System.out.println("+-----------------------------+");

        System.out.println("Answer = len(time)+1 + len(bell)+1 = 4+1 + 4+1 = 10");
    }

    /**
     * Step-by-step complexity analysis for the implemented solution.
     * <p>Let N be the number of words and L be the total number of characters across all words.
     * <p>1) Insert words into the set: Each string hashing and insertion amortizes to O(length), totaling O(L).
     * <p>2) Generate/remove proper suffixes: For a word of length k, generate k-1 suffixes. Across all words,
     * this sums to at most L - N suffixes; hashing/removal amortizes to O(total characters) -> O(L).
     * <p>3) Final summation over remaining words: Bounded by O(L) since lengths sum to ≤ L.
     * <p>Therefore, total expected time is O(L) with standard string hashing, and space is O(L) for the set.
     */
    private static void complexityProof() {
        System.out.println("Complexity Proof:");
        System.out.println("Let L be total characters over input words.");
        System.out.println("1) Add to set: O(L) hashing/insert across all strings.");
        System.out.println("2) Generate/remove suffixes: each character participates in a small constant number of hashes -> O(L).");
        System.out.println("3) Final sum over remaining words: O(L) bounded.");
        System.out.println("Total time: O(L). Space: O(L) for the set.");
    }
}
