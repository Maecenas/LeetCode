package com.leetcode;

/*
https://leetcode.com/problems/binary-tree-right-side-view/
Medium. Tree, Depth-first Search, Breadth-first Search.

Given a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @see _102_BinaryTreeLevelOrderTraversal
 */
class _199_BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        final List<Integer> res = new ArrayList<>();
        final Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode candidate = null;

        queue.offer(root);
        while (!queue.isEmpty()) {

            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (candidate == null) {
                    candidate = node;
                }
                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
            }
            res.add(candidate.val);
            candidate = null;
        }
        return res;
    }
}
