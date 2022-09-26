package com.leetcode;

/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
Medium. Array, Hash Table, Divide and Conquer, Tree, Binary Tree.

Given two integer arrays, preorder and postorder where preorder is the
preorder traversal of a binary tree of distinct values and postorder is the
postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.

Example 1:
 Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 Output: [1,2,3,4,5,6,7]

Example 2:
 Input: preorder = [1], postorder = [1]
 Output: [1]

Constraints:
 1 <= preorder.length <= 30
 1 <= preorder[i] <= preorder.length
 All the values of preorder are unique.
 postorder.length == preorder.length
 1 <= postorder[i] <= postorder.length
 All the values of postorder are unique.
 It is guaranteed that preorder and postorder are the preorder traversal
   and postorder traversal of the same binary tree.
*/

import com.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see _105_ConstructBinaryTreeFromPreorderAndInorderTraversal
 * @see _106_ConstructBinaryTreeFromInorderAndPostorderTraversal
 */
class _889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if (preorder == null || postorder == null || preorder.length != postorder.length
                || preorder.length == 0 || preorder.length > 30) return null;

        final Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                valToIndex, 0, postorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int preStart, int preEnd,
                                  Map<Integer, Integer> valToIndex, int postStart, int postEnd) {
        if (preStart > preEnd) return null;
        if (preStart == preEnd) return new TreeNode(preorder[preStart]);
        int rootVal = preorder[preStart];
        int leftRootVal = preorder[preStart + 1];
        int index = valToIndex.get(leftRootVal);
        int leftSize = index - postStart + 1;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                valToIndex, postStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                valToIndex, index + 1, postEnd - 1);
        return root;
    }
}
