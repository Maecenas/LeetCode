package com.leetcode;

/*
https://leetcode.com/problems/n-queens-ii/
Hard. Backtracking.

The n-queens puzzle is the problem of placing n queens on an n x n chessboard
such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens
puzzle.

Example 1:
 Input: n = 4
 Output: 2
 Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

Example 2:
 Input: n = 1
 Output: 1

Constraints:
 1 <= n <= 9
*/

import java.util.Arrays;

/**
 * @see _51_NQueens
 */
class _52_NQueensII {

    public static int totalNQueens(int n) {
        if (n <= 0 || n > 9) return 0;

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        return backtrack(board, 0);
    }

    private static int backtrack(char[][] board, int row) {
        if (row == board.length) {
            return 1;
        }
        int solutions = 0;
        for (int col = 0; col < board.length; col++) {
            if (!valid(board, row, col)) continue;

            board[row][col] = 'Q';
            solutions += backtrack(board, row + 1);
            board[row][col] = '.';
        }
        return solutions;
    }

    private static boolean valid(char[][] board, int row, int col) {
        for (int r = row - 1; r >= 0; r--) {
            if (board[r][col] == 'Q') return false;
        }
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') return false;
        }
        for (int r = row - 1, c = col + 1; r >= 0 && c < board.length; r--, c++) {
            if (board[r][c] == 'Q') return false;
        }
        return true;
    }
}
