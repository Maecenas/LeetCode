package LeetCode;

/*
https://leetcode.com/problems/find-mode-in-binary-search-tree/
Easy. Tree.

Given a binary search tree (BST) with duplicates, find all the mode(s)
(the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than
or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than
or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2

return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up:
Could you do that without using any extra space? (Assume that the
implicit stack space incurred due to recursion does not count).
*/

import LeetCode.utils.TreeNode;

/**
 * @see _98_ValidateBinarySearchTree
 */
class _501_FindModeInBinarySearchTree {

    private int currVal, currCount, maxCount, modeCount = 0;
    private int[] modes;

    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

    /**
     * Morris traversal
     */
    private void inorder2(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                handleValue(node.val);
                node = node.right;
            } else {
                TreeNode prev = node.left;
                while (prev.right != null && prev.right != node)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left;
                } else {
                    prev.right = null;
                    handleValue(node.val);
                    node = node.right;
                }
            }
        }
    }
}
