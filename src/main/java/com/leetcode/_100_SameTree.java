package com.leetcode;

/*
https://leetcode.com/problems/same-tree/
Easy. Tree, Depth-first Tree.

Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical
and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true

Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false

Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

class _100_SameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (q == null || p == null) return false;
        else if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (isInvalid(p, q)) return false;

        final Deque<TreeNode> deqP = new ArrayDeque<>(), deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (isInvalid(p, q)) return false;
            if (p != null) {
                if (isInvalid(p.left, q.left)) return false;
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (isInvalid(p.right, q.right)) return false;
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }

    private static boolean isInvalid(TreeNode p, TreeNode q) {
        if (p == null && q == null) return false;
        else if (q == null || p == null) return true;
        else return p.val != q.val;
    }
}
