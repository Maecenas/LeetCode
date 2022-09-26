package com.leetcode;

/*
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
Medium. Array, Tree, Depth-first Search.

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given:

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]

Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/

import com.leetcode.utils.TreeNode;

import java.util.HashMap;

/**
 * @see _105_ConstructBinaryTreeFromPreorderAndInorderTraversal
 * @see _889_ConstructBinaryTreeFromPreorderAndPostorderTraversal
 */
class _106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0 || inorder == null
                || postorder.length != inorder.length) return null;

        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(postorder, map, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] postorder, HashMap<Integer, Integer> map, int postStart, int postEnd, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;

        int inOrderIndex = map.get(postorder[postEnd]);
        TreeNode node = new TreeNode(postorder[postEnd]);
        node.left = buildTree(postorder, map, postStart, postStart + inOrderIndex - inStart - 1, inStart, inOrderIndex - 1);
        node.right = buildTree(postorder, map, postStart + inOrderIndex - inStart, postEnd - 1, inOrderIndex + 1, inEnd);
        return node;
    }
}
