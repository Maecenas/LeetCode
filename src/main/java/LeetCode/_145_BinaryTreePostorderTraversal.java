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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @see _94_BinaryTreeInorderTraversal
 * @see _144_BinaryTreePreorderTraversal
 */
class _145_BinaryTreePostorderTraversal {

    public static List<Integer> postorderTraversal(TreeNode node) {
        if (node == null) return new ArrayList<>();

        final ArrayList<Integer> res = new ArrayList<>();

        res.addAll(postorderTraversal(node.left));
        res.addAll(postorderTraversal(node.right));
        res.add(node.val);
        return res;
    }

    public static List<Integer> postorderTraversal2(TreeNode node) {
        LinkedList<Integer> list = new LinkedList<>();
        if (node == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;

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
