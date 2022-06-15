package com.leetcode;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
Easy. Depth-First Search, Binary Search Tree, Binary Tree.

Given a binary search tree (BST), find the lowest common ancestor (LCA)
of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor
is defined between two nodes p and q as the lowest node in T that has both p
and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 Output: 6
 Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 Output: 2
 Explanation: The LCA of nodes 2 and 4 is 2,
  since a node can be a descendant of itself according to the LCA definition.

Example 3:
 Input: root = [2,1], p = 2, q = 1
 Output: 2

Constraints:
 The number of nodes in the tree is in the range [2, 10^5].
 -10^9 <= Node.val <= 10^9
 All Node.val are unique.
 p != q
 p and q will exist in the BST.
*/

import com.leetcode.utils.TreeNode;

/**
 * @see _236_LowestCommonAncestorOfABinaryTree
 * @see _1644_LowestCommonAncestorOfABinaryTreeII
 * @see _1650_LowestCommonAncestorOfABinaryTreeIII
 * @see _1676_LowestCommonAncestorOfABinaryTreeIV
 */
class _235_LowestCommonAncestorOfABinarySearchTree {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == q) return null;

        return find(root, Math.min(p.val, q.val), Math.max(p.val, q.val));
    }

    private static TreeNode find(final TreeNode root, final int lo, final int hi) {
        if (root == null) return null;

        if (root.val > hi) {
            return find(root.left, lo, hi);
        } else if (root.val < lo) {
            return find(root.right, lo, hi);
        } else /* if (lo <= root.val && root.val <= hi ) */ {
            return root;
        }
    }
}
