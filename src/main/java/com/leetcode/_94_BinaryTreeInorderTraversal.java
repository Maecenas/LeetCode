package com.leetcode;

/*
https://leetcode.com/problems/binary-tree-inorder-traversal/
Medium. Hash Table, Stack, Tree.

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @see _144_BinaryTreePreorderTraversal
 * @see _145_BinaryTreePostorderTraversal
 * @see _173_BinarySearchTreeIterator.BSTIteratorMorris
 */
class _94_BinaryTreeInorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode node) {
        final List<Integer> list = new ArrayList<>();
        if (node == null) return list;

        list.addAll(inorderTraversal(node.left));
        list.add(node.val);
        list.addAll(inorderTraversal(node.right));
        return list;
    }

    public static List<Integer> inorderTraversal2(TreeNode node) {
        final List<Integer> list = new ArrayList<>();
        if (node == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                // Add after all left children
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }

    /**
     * Morris Traversal
     * O(n), O(1)
     */
    public static List<Integer> inorderTraversalMorris(TreeNode node) {
        if (node == null) return new ArrayList<>();

        final List<Integer> res = new ArrayList<>();
        TreeNode tourist = node, guide;
        while (tourist != null) {
            guide = tourist.left;
            if (tourist.left != null) {
                // guide go to the rightmost node
                while (guide.right != null && guide.right != tourist) {
                    guide = guide.right;
                }
                // create a bridge to the right
                if (guide.right == null) {
                    guide.right = tourist;
                    tourist = tourist.left;
                } else /* if (guide.right == tourist) */ {
                    // bridge detected, finished left traversal
                    guide.right = null;
                    res.add(tourist.val);
                    tourist = tourist.right;
                }
            } else {
                res.add(tourist.val);
                tourist = tourist.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(6);
        root.right = new TreeNode(11);
        root.right.left = new TreeNode(10);

        System.out.println("inorderTraversal(root) = " + inorderTraversal(root));
        System.out.println("inorderTraversal2(root) = " + inorderTraversal2(root));
        System.out.println("inorderTraversalMorris(root) = " + inorderTraversalMorris(root));
    }
}
