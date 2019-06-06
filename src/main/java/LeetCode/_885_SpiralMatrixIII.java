package LeetCode;

/*
https://leetcode.com/problems/spiral-matrix-iii/
Medium. Math.

On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column,
and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid.

Whenever we would move outside the boundary of the grid, we continue our walk
outside the grid (but may return to the grid boundary later.)

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid
in the order they were visited.

Example 1:

Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]

Example 2:

Input: R = 5, C = 6, r0 = 1, c0 = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]

Note:

1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C
*/

/**
 * @see _54_SpiralMatrix
 * @see _59_SpiralMatrixII
 */
class _885_SpiralMatrixIII {

    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {1, 0, -1, 0};

    public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        if (R <= 0 || C <= 0 || r0 >= R || c0 >= C) return new int[0][];
        else if (R == 1 && C == 1) return new int[][]{{r0, c0}};

        final int[][] res = new int[R * C][2];
        res[0] = new int[]{r0, c0};

        int count = 1;

        for (int length = 1; length < 2 * (R + C); length += 2) {
            for (int direction = 0; direction < 4; direction++) {
                // number of steps in this direction
                int steps = length + (direction >> 1);
                for (int step = 0; step < steps; step++) {
                    r0 += dr[direction];
                    c0 += dc[direction];
                    if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
                        res[count] = new int[]{r0, c0};
                        count++;
                        if (count == R * C) return res;
                    }
                }
            }
        }

        return new int[0][];
    }

    public static int[][] spiralMatrixIII2(int R, int C, int r0, int c0) {
        if (R <= 0 || C <= 0 || r0 >= R || c0 >= C) return new int[0][];
        else if (R == 1 && C == 1) return new int[][]{{r0, c0}};

        final int[][] res = new int[R * C][2];
        res[0] = new int[]{r0, c0};

        int count = 1;
        int dr = 0, dc = 1;
        int stepHelper = 0, step = 0;
        while (count < R * C) {
            r0 += dr;
            c0 += dc;
            step++;
            if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
                res[count] = new int[]{r0, c0};
                count++;
            }
            if (step == (stepHelper >> 1) + 1) {
                step = 0;
                stepHelper++;

                // cross product (x, y, 0) × (0, 0, 1)
                // (x, y) -> (y, -x)
                // (0, 1) -> (1, 0) -> (0, -1) -> (-1, 0) -> (0, 1)
                //tmp = dr;
                //dr = dc;
                //dc = -tmp;

                // (x, y - x) -> (y, y - x) -> (y, -x)
                //dc -= dr;
                //dr += dc;
                //dc -= dr;

                // (x, y ^ x) -> (y, y ^ x) -> (y, -x)
                dc ^= dr;
                dr ^= dc;
                dc = -(dr ^ dc);
            }
        }
        return res;
    }
}
