package com.leetcode;

/*
https://leetcode.com/problems/unique-binary-search-trees-ii/
Medium. Dynamic Programming, Tree.

Given an integer n, generate all structurally unique BST's
(binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see _96_UniqueBinarySearchTrees
 */
class _95_UniqueBinarySearchTreesII {

    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();

        return generateTrees(1, n);
    }

    private static List<TreeNode> generateTrees(int start, int end) {
        final ArrayList<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
        } else if (start == end) {
            res.add(new TreeNode(start));
        } else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftList = generateTrees(start, i - 1);
                List<TreeNode> rightList = generateTrees(i + 1, end);
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Dynamic-Programming, caching-friendly
     */
    public static List<TreeNode> generateTrees2(int n) {
        if (n <= 0) return new ArrayList<>();

        ArrayList<TreeNode>[] result = (ArrayList<TreeNode>[]) new ArrayList[n + 1];
        result[0] = new ArrayList<>();
        result[0].add(null);

        for (int i = 1; i <= n; i++) {
            result[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (TreeNode left : result[j]) {
                    for (TreeNode right : result[i - j - 1]) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = left;
                        node.right = clone(right, j + 1);
                        result[i].add(node);
                    }
                }
            }
        }
        return result[n];
    }

    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) return null;

        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }
}
