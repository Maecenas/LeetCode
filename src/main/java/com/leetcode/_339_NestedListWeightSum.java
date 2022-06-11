package com.leetcode;

/*
https://leetcode.com/problems/nested-list-weight-sum/
Medium. Depth-first Search.

Given a nested list of integers, return the sum of all integers
in the list weighted by their depth.

Each element is either an integer, or a list --
whose elements may also be integers or other lists.

Example 1:
 Input: [[1,1],2,[1,1]]
 Output: 10
 Explanation: Four 1's at depth 2, one 2 at depth 1.

Example 2:
 Input: [1,[4,[6]]]
 Output: 27
 Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
*/

import java.util.ArrayList;
import java.util.List;


class _339_NestedListWeightSum {

    /**
     * This is the interface that allows for creating nested lists.
     * You should not implement it, or speculate about its implementation;
     */
    interface NestedInteger {
        // Constructor initializes an empty nested list.
        //public NestedInteger();

        // Constructor initializes a single integer.
        //public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    public static int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;

        int res = 0;
        for (NestedInteger nestedInteger : nestedList) {
            res += depthSum(nestedInteger, 1);
        }
        return res;
    }

    private static int depthSum(final NestedInteger nestedInteger, final int depth) {
        if (nestedInteger == null) return 0;

        if (nestedInteger.isInteger())  {
            return depth * nestedInteger.getInteger();
        } else {
            int res = 0;
            for (NestedInteger ni : nestedInteger.getList()) {
                res += depthSum(ni, depth + 1);
            }
            return res;
        }
    }

    /**
     * Test stub
     */
    private static class MyNestedIntegerImpl implements NestedInteger {

        private Integer integer;
        private List<NestedInteger> list;

        public MyNestedIntegerImpl() {
            this.integer = null;
            this.list = new ArrayList<>();
        }

        public MyNestedIntegerImpl(int value) {
            this.integer = value;
            this.list = null;
        }

        @Override
        public boolean isInteger() {
            return integer != null;
        }

        @Override
        public Integer getInteger() {
            return integer;
        }

        @Override
        public void setInteger(int value) {
            this.integer = value;
            this.list = null;
        }

        @Override
        public void add(NestedInteger ni) {
            if (this.list == null) {
                this.integer = null;
                this.list = new ArrayList<>();
            }
            this.list.add(ni);
        }

        @Override
        public List<NestedInteger> getList() {
            return this.list;
        }
    }
}
