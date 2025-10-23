package com.simon.hot100;

public class MaximalSquareTDD {

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     * <p>
     * This problem can be solved efficiently using dynamic programming. The core idea is to define a DP table where each cell
     * represents the side length of the largest square ending at that position.
     *
     * <h3>Pyramid Principle Explanation</h3>
     * <ul>
     *   <li><b>Top of the Pyramid (The Answer):</b> The problem is best solved with dynamic programming because it has optimal substructure and overlapping subproblems.</li>
     *   <li><b>Key Supporting Points (The "Why"):</b>
     *     <ul>
     *       <li><b>Optimal Substructure:</b> The largest square ending at cell (i, j) is determined by the sizes of the largest squares ending at its neighbors: (i-1, j), (i, j-1), and (i-1, j-1).</li>
     *       <li><b>Overlapping Subproblems:</b> The calculation for the largest square at a given cell is reused in the calculations for its neighboring cells.</li>
     *     </ul>
     *   </li>
     *   <li><b>Base of the Pyramid (The "How"):</b>
     *     <ul>
     *       <li>We define a DP state `dp[i][j]` as the side length of the largest square of '1's whose bottom-right corner is at `matrix[i-1][j-1]`.</li>
     *       <li>The state transition equation is:
     *         <ul>
     *           <li>If `matrix[i-1][j-1] == '0'`, then `dp[i][j] = 0`.</li>
     *           <li>If `matrix[i-1][j-1] == '1'`, then `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`.</li>
     *         </ul>
     *       </li>
     *       <li>We iterate through the matrix, fill the DP table, and keep track of the maximum side length found. The final answer is the square of this maximum length.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param matrix A 2D char array representing the binary matrix.
     * @return The area of the largest square containing only 1's.
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxSide = 0;

        // ASCII Art Illustration of DP table update
        // Let's consider a cell (i, j) where matrix[i-1][j-1] = '1'
        //
        // dp table state before calculating dp[i][j]:
        // +---+---+
        // | A | B |
        // +---+---+
        // | C | ? |
        // +---+---+
        //
        // A = dp[i-1][j-1] (top-left)
        // B = dp[i-1][j]   (top)
        // C = dp[i][j-1]   (left)
        //
        // If matrix[i-1][j-1] is '1', then the new square at (i,j) can extend from the squares
        // ending at A, B, and C. The new side length is limited by the smallest of these three.
        //
        // dp[i][j] = 1 + min(A, B, C)
        //
        // Final state:
        // +---+---+
        // | A | B |
        // +---+---+
        // | C | D |
        // +---+---+
        // D = 1 + min(A, B, C)

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }

    /**
     * Main method for TDD-style testing.
     * <p>
     * This method contains several test cases to verify the correctness of the `maximalSquare` method.
     * It prints "PASS" for successful tests and "FAIL" for failed tests, along with the expected and actual results.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        MaximalSquareTDD solution = new MaximalSquareTDD();
        int testCaseNumber = 1;

        // Test Case 1: Standard case
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        int expected1 = 4;
        test(solution, testCaseNumber++, matrix1, expected1);

        // Test Case 2: Empty matrix
        char[][] matrix2 = {};
        int expected2 = 0;
        test(solution, testCaseNumber++, matrix2, expected2);

        // Test Case 3: Matrix with only one row
        char[][] matrix3 = {{'1', '1', '1', '1'}};
        int expected3 = 1;
        test(solution, testCaseNumber++, matrix3, expected3);

        // Test Case 4: Matrix with only one column
        char[][] matrix4 = {{'1'}, {'1'}, {'1'}};
        int expected4 = 1;
        test(solution, testCaseNumber++, matrix4, expected4);

        // Test Case 5: Matrix with no '1's
        char[][] matrix5 = {
            {'0', '0', '0'},
            {'0', '0', '0'}
        };
        int expected5 = 0;
        test(solution, testCaseNumber++, matrix5, expected5);

        // Test Case 6: Matrix full of '1's
        char[][] matrix6 = {
            {'1', '1'},
            {'1', '1'}
        };
        int expected6 = 4;
        test(solution, testCaseNumber++, matrix6, expected6);
    }

    private static void test(MaximalSquareTDD solution, int testCaseNumber, char[][] matrix, int expected) {
        int result = solution.maximalSquare(matrix);
        if (result == expected) {
            System.out.printf("Test Case %d: PASS%n", testCaseNumber);
        } else {
            System.out.printf("Test Case %d: FAIL (Expected: %d, Got: %d)%n", testCaseNumber, expected, result);
        }
    }
}