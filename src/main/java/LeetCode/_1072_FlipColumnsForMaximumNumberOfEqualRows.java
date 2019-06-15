package LeetCode;

/*
https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/
Medium. Hash Table.

Given a matrix consisting of 0s and 1s, we may choose any number of columns
in the matrix and flip every cell in that column. Flipping a cell changes
the value of that cell from 0 to 1 or from 1 to 0.

Return the maximum number of rows that have all values equal after some number of flips.

Example 1:

Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.

Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1
*/

import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;

class _1072_FlipColumnsForMaximumNumberOfEqualRows {

    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        final int R = matrix.length, C = matrix[0].length;
        final HashMap<BitSet, Integer> map = new HashMap<>();

        for (int[] row : matrix) {
            final BitSet bs = new BitSet(C);
            for (int c = 0; c < C; c++) {
                bs.set(c, row[c] == row[0]);
            }
            map.put(bs, map.getOrDefault(bs, 0) + 1);
        }
        return Collections.max(map.values());
    }
}
