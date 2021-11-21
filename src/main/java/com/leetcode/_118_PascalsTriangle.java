package com.leetcode;

/*
https://leetcode.com/problems/pascals-triangle/
Easy. Array.

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

import java.util.ArrayList;
import java.util.List;

class _118_PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {
        final List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0) return res;

        // can even be reduced to use a single List
        // see #119 Pascal's Triangle II
        List<Integer> currRow = new ArrayList<>(), prevRow = currRow;
        // numRows == 1
        currRow.add(1);
        res.add(currRow);
        for (int i = 1; i < numRows; i++) {
            currRow = new ArrayList<>(prevRow.size() + 1);
            currRow.add(1);
            for (int j = 0; j < prevRow.size() - 1; j++) {
                currRow.add(prevRow.get(j) + prevRow.get(j + 1));
            }
            currRow.add(1);
            res.add(currRow);
            prevRow = currRow;
        }
        return res;
    }
}
