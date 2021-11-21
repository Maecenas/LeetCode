package com.leetcode;

/*
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
Medium. Stack, Tree, Breath-first Search.
Given a binary tree, return the zigzag level order traversal of its nodes' values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

class _103_BinaryTreeZigzagLevelOrderTraversal {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        final Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean needReverse = false;

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (needReverse) Collections.reverse(list);
            needReverse = !needReverse;
            if (!list.isEmpty()) res.add(list);
        }
        return res;
    }
}
