package LeetCode;

/*
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
Easy. Tree, Depth-first Search.

Given an array where elements are sorted in ascending order,
convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a
binary tree in which the depth of the two subtrees of every node
never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5],
which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
*/

import LeetCode.utils.TreeNode;

/**
 * @see _109_ConvertSortedListToBinarySearchTree
 */
class _108_ConvertSortedArrayToBinarySearchTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(final int[] nums, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + ((hi - lo) >> 1);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, lo, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, hi);
        return node;
    }
}
