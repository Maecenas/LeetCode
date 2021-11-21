package com.leetcode;

/*
https://leetcode.com/problems/pascals-triangle-ii
Easy. Array.

Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]

Follow up:

Could you optimize your algorithm to use only O(k) extra space?
*/

import java.util.LinkedList;
import java.util.List;

class _119_PascalsTriangleII {

    public static List<Integer> getRow(int rowIndex) {
        final List<Integer> res = new LinkedList<>();
        if (rowIndex <= 0) return res;

        for (int row = 0; row <= rowIndex; row++) {
            // Use LinkedList for inserting at head and shifting right by 1
            res.add(0, 1);
            for (int i = 1; i < row; i++) {
                res.set(i, res.get(i) + res.get(i + 1));
            }
        }
        return res;
    }
}
