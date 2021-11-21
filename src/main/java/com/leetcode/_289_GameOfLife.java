package com.leetcode;

/*
https://leetcode.com/problems/game-of-life/
Medium. Array.

According to the <a src="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">Wikipedia's article</a>:
"The Game of Life, also known simply as Life, is a cellular automaton devised
by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board
given its current state. The next state is created by applying the above rules
simultaneously to every cell in the current state,
where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]

Follow up:

Could you solve it in-place? Remember that the board needs to be updated
at the same time: You cannot update some cells first
and then use their updated values to update other cells.

In this question, we represent the board using a 2D array. In principle,
the board is infinite, which would cause problems when the active area
encroaches the border of the array. How would you address these problems?
*/

class _289_GameOfLife {

    public static void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        final int R = board.length, C = board[0].length;

        // Iterate through board cell by cell.
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // For each cell count the number of live neighbors.
                int lives = countLiveNeighbors(board, R, C, r, c);

                if ((board[r][c] == 1) && (lives < 2 || lives > 3)) {
                    // Rule 1 or Rule 3
                    // -1 signifies the cell is now dead but originally was live.
                    board[r][c] = -1;
                } else if (board[r][c] == 0 && lives == 3) {
                    // Rule 4
                    // 2 signifies the cell is now live but was originally dead.
                    board[r][c] = 2;
                }
            }
        }

        // Get the final representation for the newly updated board.
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] > 0) {
                    board[r][c] = 1;
                } else {
                    board[r][c] = 0;
                }
            }
        }
    }

    private static int countLiveNeighbors(final int[][] board, final int R, final int C, int r, int c) {
        return ((r - 1 >= 0 && c - 1 >= 0 && Math.abs(board[r - 1][c - 1]) == 1) ? 1 : 0)
                + ((r - 1 >= 0 && Math.abs(board[r - 1][c]) == 1) ? 1 : 0)
                + ((r - 1 >= 0 && c + 1 < C && Math.abs(board[r - 1][c + 1]) == 1) ? 1 : 0)
                + ((c - 1 >= 0 && c - 1 < C && Math.abs(board[r][c - 1]) == 1) ? 1 : 0)
                + ((c + 1 < C && Math.abs(board[r][c + 1]) == 1) ? 1 : 0)
                + ((r + 1 < R && c - 1 >= 0 && Math.abs(board[r + 1][c - 1]) == 1) ? 1 : 0)
                + ((r + 1 < R && Math.abs(board[r + 1][c]) == 1) ? 1 : 0)
                + ((r + 1 < R && c + 1 < C && Math.abs(board[r + 1][c + 1]) == 1) ? 1 : 0);
    }
}
