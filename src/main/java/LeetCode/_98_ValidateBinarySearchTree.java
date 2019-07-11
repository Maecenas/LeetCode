package LeetCode;

/*
https://leetcode.com/problems/validate-binary-search-tree/
Medium. Tree, Depth-first Search.

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true

Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/

import LeetCode.utils.TreeNode;

import java.util.LinkedList;

/**
 * @see _94_BinaryTreeInorderTraversal
 * @see _501_FindModeInBinarySearchTree
 */
class _98_ValidateBinarySearchTree {

    public static boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;

        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!isValidBST(node.right, val, upper)) return false;
        if (!isValidBST(node.left, lower, val)) return false;
        return true;
    }

    public static boolean isValidBST2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;

        final LinkedList<TreeNode> stack = new LinkedList<>();
        final LinkedList<Integer> uppers = new LinkedList<>(), lowers = new LinkedList<>();

        Integer lower = null, upper = null, val;
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;

            stack.add(root.right);
            lowers.add(val);
            uppers.add(upper);

            stack.add(root.left);
            lowers.add(lower);
            uppers.add(val);
        }
        return true;
    }
}
