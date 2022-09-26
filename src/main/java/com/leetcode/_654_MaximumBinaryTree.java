package com.leetcode;

/*
https://leetcode.com/problems/maximum-binary-tree/
Medium. Array, Divide and Conquer, Stack, Tree, Monotonic Stack, Binary Tree.

You are given an integer array nums with no duplicates. A maximum binary tree
can be built recursively from nums using the following algorithm:

 Create a root node whose value is the maximum value in nums.
 Recursively build the left subtree on the subarray prefix to the left of the
   maximum value.
 Recursively build the right subtree on the subarray suffix to the right of
   the maximum value.

Return the maximum binary tree built from nums.

Example 1:
 Input: nums = [3,2,1,6,0,5]
 Output: [6,3,5,null,2,0,null,null,1]
 Explanation: The recursive calls are as follow:
 - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right
   suffix is [0,5].
     - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix
       is [2,1].
         - Empty array, so no child.
         - The largest value in [2,1] is 2. Left prefix is [] and right suffix
           is [1].
             - Empty array, so no child.
             - Only one element, so child is a node with value 1.
     - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.

Example 2:
 Input: nums = [3,2,1]
 Output: [3,null,2,null,1]

Constraints:
 1 <= nums.length <= 1000
 0 <= nums[i] <= 1000
 All integers in nums are unique.
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

class _654_MaximumBinaryTree {

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length > 1_000) return null;

        return construct(nums, 0, nums.length - 1);
    }

    private static TreeNode construct(int[] nums, int lo, int hi) {
        if (lo > hi) return null;

        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        final TreeNode root = new TreeNode(maxVal);
        root.left = construct(nums, lo, index - 1);
        root.right = construct(nums, index + 1, hi);

        return root;
    }

    public static TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length > 1_000) return null;

        // decreasing stack
        final Deque<TreeNode> stack = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode curr = new TreeNode(num);
            while (!stack.isEmpty() && stack.peek().val < num) {
                curr.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }

        while (stack.size() > 1) stack.pop();
        return stack.pop();
    }
}
