package LeetCode;

/*
https://leetcode.com/problems/word-search/
Medium. Array, Backtracking.

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

class _79_WordSearch {

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;

        final char[] c = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, c, 0, i, j)) return true;
            }
        }
        return false;
    }

    private static boolean exist(final char[][] board, final char[] c, int idx, int i, int j) {
        if (idx == c.length) {
            return true;
        } else if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != c[idx])
            return false;
        else {
            // uppercase characters use only 7 least important bits
            // for 65 - 90 (0x41 - 0x5A)
            // add a sign for the 8th LSD acting as a boolean visited
            //board[i][j] ^= 0x100;
            board[i][j] |= 0x80;
            boolean exist = exist(board, c, idx + 1, i, j + 1)
                    || exist(board, c, idx + 1, i, j - 1)
                    || exist(board, c, idx + 1, i + 1, j)
                    || exist(board, c, idx + 1, i - 1, j);
            //board[i][j] ^= 0x100;
            board[i][j] &= 0x7F;
            return exist;
        }
    }
}
