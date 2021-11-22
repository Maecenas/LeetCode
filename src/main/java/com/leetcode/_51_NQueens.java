package com.leetcode;

/*
https://leetcode.com/problems/n-queens/
Hard. Array, Backtracking.

The n-queens puzzle is the problem of placing n queens on an n x n chessboard
such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.
You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement,
where 'Q' and '.' both indicate a queen and an empty space, respectively.

Example 1:
 Input: n = 4
 Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 Explanation: There exist two distinct solutions to the 4-queens puzzle
  as shown above

Example 2:
 Input: n = 1
 Output: [["Q"]]

Constraints:
 1 <= n <= 9
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see _52_NQueensII
 */
class _51_NQueens {

    /**
     * Backtracking
     * O(n!), O(n^2)
     */
    public static List<List<String>> solveNQueens(int n) {
        if (n <= 0 || n > 9) return new ArrayList<>();

        final List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, res, 0);
        return res;
    }

    private static void backtrack(char[][] board, final List<List<String>> res, int row) {
        if (row == board.length) {
            res.add(asList(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (!valid(board, row, col)) continue;

            board[row][col] = 'Q';
            backtrack(board, res, row + 1);
            board[row][col] = '.';
        }
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

    private static List<String> asList(char[][] array) {
        final List<String> res = new ArrayList<>(array.length);
        for (char[] row : array) {
            res.add(String.valueOf(row));
        }
        return res;
    }
}
