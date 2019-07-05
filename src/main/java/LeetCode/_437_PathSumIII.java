package LeetCode;

/*
https://leetcode.com/problems/path-sum-iii/
Easy. Tree.

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must
go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values
are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

import LeetCode.utils.TreeNode;

import java.util.HashMap;

/**
 * @see _112_PathSum
 * @see _113_PathSumII
 */
class _437_PathSumIII {

    /**
     * Recursive
     */
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        return count(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private static int count(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0)
                + count(node.left, sum - node.val)
                + count(node.right, sum - node.val);
    }

    /**
     * Caching
     */
    public static int pathSum2(TreeNode root, int sum) {
        final HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return pathSum(preSum, root, 0, sum);
    }

    private static int pathSum(final HashMap<Integer, Integer> map, TreeNode root, int sum, int target) {
        if (root == null) return 0;

        sum += root.val;
        int res = map.getOrDefault(sum - target, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);

        res += pathSum(map, root.left, sum, target)
                + pathSum(map, root.right, sum, target);
        map.put(sum, map.get(sum) - 1);
        return res;
    }
}
