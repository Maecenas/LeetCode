package LeetCode;

/*
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
Easy. Tree, Breath-first Search.

Given a binary tree, return the bottom-up level order traversal of its nodes'
values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]
*/

import LeetCode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @see _102_BinaryTreeLevelOrderTraversal
 */
class _107_BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        final Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (!list.isEmpty()) res.add(list);
        }
        Collections.reverse(res);
        return res;
    }
}
