package LeetCode;

/*
https://leetcode.com/problems/binary-tree-level-order-traversal/
Medium. Tree, Breath-first Search.

Given a binary tree, return the level order traversal of its nodes' values.
(ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
*/

import LeetCode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @see _103_BinaryTreeZigzagLevelOrderTraversal
 * @see _107_BinaryTreeLevelOrderTraversalII
 */
class _102_BinaryTreeLevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        final Queue<TreeNode> queue = new LinkedList<>();
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
        return res;
    }
}
