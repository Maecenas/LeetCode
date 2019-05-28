package LeetCode;

/*
https://leetcode.com/problems/search-a-2d-matrix/
Medium. Array, Binary Search.

Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true

Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
*/

class _74_SearchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        final int R = matrix.length, C = matrix[0].length;
        // index = row * C + column
        int lo = 0, hi = R * C - 1, mid;
        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            int cmp = matrix[mid / C][mid % C];
            if (cmp == target) {
                return true;
            } else if (cmp > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        // check value on lo is saver than simply return false
        return matrix[lo / C][lo % C] == target;
    }
}
