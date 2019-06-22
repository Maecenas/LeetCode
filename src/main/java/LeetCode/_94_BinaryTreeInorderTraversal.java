package LeetCode;

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

import LeetCode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class _94_BinaryTreeInorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        final List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        final List<Integer> list = new LinkedList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                // Add after all left children
                list.add(curr.val);
                curr = curr.right;
            }
        }
        return list;
    }
}
