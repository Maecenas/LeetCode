package com.leetcode;

/*
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
Hard. Hash Table, Tree, Depth-First Search, Breadth-First Search.

Given the root of a binary tree, calculate the vertical order traversal of
the binary tree.

For each node at position (row, col), its left and right children will be at
positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of
the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom
orderings for each column index starting from the leftmost column and ending
on the rightmost column. There may be multiple nodes in the same row and same column.
In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.

Example 2:

Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.

Example 3:

Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same
location and should be ordered by their values.

Constraints:
 The number of nodes in the tree is in the range [1, 1000].
 0 <= Node.val <= 1000
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @see _314_BinaryTreeVerticalOrderTraversal
 */
class _987_VerticalOrderTraversalOfABinaryTree {

    private static class IndexedNode {
        final int col, row, val;
        IndexedNode(int col, int row, int val) { this.col = col; this.row = row; this.val = val; }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        final Map<TreeNode, Integer> column = new HashMap<>();
        column.put(root, 0);
        final PriorityQueue<IndexedNode> pq = new PriorityQueue<>(
                Comparator.comparingInt((IndexedNode n) -> n.col)
                        .thenComparingInt(n -> n.row)
                        .thenComparingInt(n -> n.val)
        );
        final Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int row = 0;

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                final TreeNode node = queue.poll();
                final int col = column.get(node);
                if (node.left != null) {
                    queue.offer(node.left);
                    column.put(node.left, col - 1);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    column.put(node.right, col + 1);
                }
                pq.offer(new IndexedNode(col, row, node.val));
            }
            row++;
        }

        final List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int col = pq.peek().col;

        while (!pq.isEmpty()) {
            IndexedNode node = pq.poll();
            if (node.col != col) {
                res.add(new ArrayList<>());
                col = node.col;
            }
            res.get(res.size() - 1).add(node.val);
        }
        return res;
    }
}
