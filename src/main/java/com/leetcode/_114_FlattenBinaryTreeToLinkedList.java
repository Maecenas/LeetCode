package com.leetcode;

/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
Medium. Linked List, Stack, Tree, Depth-First Search, Binary Tree.

Given the root of a binary tree, flatten the tree into a "linked list":

 The "linked list" should use the same TreeNode class
   where the right child pointer points to the next node in the list
   and the left child pointer is always null.
 The "linked list" should be in the same order as a pre-order traversal of
   the binary tree.

Example 1:
 Input: root = [1,2,5,3,4,null,6]
 Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
 Input: root = []
 Output: []

Example 3:
 Input: root = [0]
 Output: [0]

Constraints:
 The number of nodes in the tree is in the range [0, 2000].
 -100 <= Node.val <= 100

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
*/

import com.leetcode.utils.TreeNode;

/**
 * @see _94_BinaryTreeInorderTraversal#inorderTraversalMorris
 * @see _173_BinarySearchTreeIterator.BSTIteratorMorris
 */
class _114_FlattenBinaryTreeToLinkedList {

    public static void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = right;
    }

    /**
     * Morris Traversal
     * O(n), O(1)
     */
    public static void flattenMorris(TreeNode root) {
        if (root == null) return;

        TreeNode tourist = root, guide;
        while (tourist != null) {
            // If the tourist has a left child
            if (tourist.left != null) {
                // Find the rightmost node
                guide = tourist.left;
                while (guide.right != null) {
                    guide = guide.right;
                }

                guide.right = tourist.right;
                tourist.right = tourist.left;
                tourist.left = null;
            }
            tourist = tourist.right;
        }
    }
}
