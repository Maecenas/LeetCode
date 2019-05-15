package LeetCode;

/*
https://leetcode.com/problems/binary-tree-postorder-traversal/
Hard. Stack, Tree.

Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

import LeetCode.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 */
class _145_BinaryTreePostorderTraversal {

    public static List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                list.addFirst(curr.val);  // Reverse the process of post-order
                curr = curr.right;        // Reverse the process of post-order
            } else {
                curr = stack.pop().left;  // Reverse the process of post-order
            }
        }
        return list;
    }
}
