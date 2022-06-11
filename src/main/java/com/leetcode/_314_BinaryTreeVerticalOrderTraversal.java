package com.leetcode;

/*
https://leetcode.com/problems/binary-tree-vertical-order-traversal/
Medium. Hash Table, Tree, Depth-First Search, Breadth-First Search, Binary Tree.

Given the root of a binary tree, return the vertical order traversal
of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Example 1:
 Input: root = [3,9,20,null,null,15,7]
 Output: [[9],[3,15],[20],[7]]

Example 2:
 Input: root = [3,9,8,4,0,1,7]
 Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:
 Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 Output: [[4],[9,5],[3,0,1],[8,2],[7]]

Constraints:
 The number of nodes in the tree is in the range [0, 100].
 -100 <= Node.val <= 100
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class _314_BinaryTreeVerticalOrderTraversal {

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        final Map<TreeNode, Integer> column = new HashMap<>();
        column.put(root, 0);
        final SortedMap<Integer, List<Integer>> vertical = new TreeMap<>();
        final Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                int col = column.get(node);
                if (node.left != null) {
                    queue.offer(node.left);
                    column.put(node.left, col - 1);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    column.put(node.right, col + 1);
                }
                vertical.computeIfAbsent(col, ignored -> new ArrayList<>())
                        .add(node.val);
            }
        }

        return new ArrayList<>(vertical.values());
    }
}
