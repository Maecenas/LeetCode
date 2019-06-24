package LeetCode;

/*
https://leetcode.com/problems/symmetric-tree/
Easy. Tree, Depth-first Search, Breath-first Search.

Given a binary tree, check whether it is a mirror of itself
(ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3

Note:
Bonus points if you could solve it both recursively and iteratively.
*/

import LeetCode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class _101_SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private static boolean isSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        else if (t1 == null || t2 == null) return false;
        else return t1.val == t2.val
                    && isSymmetric(t1.right, t2.left)
                    && isSymmetric(t1.left, t2.right);
    }

    public static boolean isSymmetric2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;

        final Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node1 = q.poll();
            TreeNode node2 = q.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;
            q.add(node1.left);
            q.add(node2.right);
            q.add(node1.right);
            q.add(node2.left);
        }
        return true;
    }
}
