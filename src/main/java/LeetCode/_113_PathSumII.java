package LeetCode;

/*
https://leetcode.com/problems/path-sum-ii/
Medium. Tree, Depth-first Tree.

Given a binary tree and a sum, find all root-to-leaf paths
where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/

import LeetCode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see _112_PathSum
 * @see _437_PathSumIII
 */
class _113_PathSumII {

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null || (root.val != sum && root.left == null && root.right == null)) return new ArrayList<>();

        final List<List<Integer>> res = new ArrayList<>();
        final List<Integer> list = new ArrayList<>();
        pathSum(res, list, root, sum);
        return res;
    }

    private static void pathSum(final List<List<Integer>> res, final List<Integer> list, TreeNode root, int sum) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new ArrayList<>(list));
        } else {
            pathSum(res, list, root.left, sum - root.val);
            pathSum(res, list, root.right, sum - root.val);
        }
        list.remove(list.size() - 1);
    }
}
