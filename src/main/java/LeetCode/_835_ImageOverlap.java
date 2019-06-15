package LeetCode;

/*
https://leetcode.com/problems/image-overlap/
Medium. Array.

Two images A and B are given, represented as binary, square matrices of the same size.
(A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down
any number of units), and place it on top of the other image. After, the overlap
of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.

Notes:

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
*/

class _835_ImageOverlap {

    public static int largestOverlap(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A.length != A[0].length
                || B == null || B.length == 0 || B.length != B[0].length
                || A.length != B.length) {
            return 0;
        }

        final int len = A.length;
        final int[][] count = new int[(len << 1) - 1][(len << 1) - 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (A[i][j] == 0) continue;
                for (int i2 = 0; i2 < len; i2++) {
                    for (int j2 = 0; j2 < len; j2++) {
                        if (B[i2][j2] == 1) {
                            // count result of mapping (i, j) -> (i2, j2)
                            count[i - i2 + len - 1][j - j2 + len - 1]++;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int[] row : count) {
            for (int c : row) {
                res = Math.max(res, c);
            }
        }
        return res;
    }
}
