package com.leetcode;

/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
Medium. Array, Tree, Depth-first Search.

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]

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
 * @see _106_ConstructBinaryTreeFromInorderAndPostorderTraversal
 */
class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private int preOrderIndex;
    private int inOrderIndex;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null
                || preorder.length != inorder.length) return null;

        return buildTree(preorder, 0, inorder, 0, preorder.length);
    }

    private static TreeNode buildTree(final int[] preorder, int preorderStart, final int[] inorder, int inorderStart, int length) {
        if (length == 0) return null;

        int inorderIndex = -1;
        for (int i = inorderStart; i < inorderStart + length; i++) {
            if (inorder[i] == preorder[preorderStart]) {
                inorderIndex = i;
                break;
            }
        }
        int leftLength = inorderIndex - inorderStart;
        TreeNode node = new TreeNode(preorder[preorderStart]);
        node.left = buildTree(preorder, preorderStart + 1, inorder, inorderStart, leftLength);
        node.right = buildTree(preorder, preorderStart + leftLength + 1, inorder, inorderIndex + 1, length - leftLength - 1);
        return node;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null
                || preorder.length != inorder.length) return null;

        return buildTree(preorder, inorder, null);
    }

    private TreeNode buildTree(final int[] preorder, final int[] inorder, TreeNode lastNode) {
        if (preOrderIndex == preorder.length || (lastNode != null && lastNode.val == inorder[inOrderIndex]))
            return null;

        TreeNode node = new TreeNode(preorder[preOrderIndex]);
        preOrderIndex++;
        node.left = buildTree(preorder, inorder, node);
        inOrderIndex++;
        node.right = buildTree(preorder, inorder, lastNode);
        return node;
    }

    /**
     * Use a Map to look up inOrder index
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null
                || preorder.length != inorder.length) return null;

        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, map, 0, 0, preorder.length - 1);
    }

    private static TreeNode buildTree(final int[] preorder, final HashMap<Integer, Integer> map, int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) return null;

        int inOrderIndex = map.get(preorder[preStart]);
        TreeNode node = new TreeNode(preorder[preStart]);
        node.left = buildTree(preorder, map, preStart + 1, inStart, inOrderIndex - 1);
        node.right = buildTree(preorder, map, preStart + (inOrderIndex - inStart) + 1, inOrderIndex + 1, inEnd);
        return node;
    }
}
