package com.leetcode;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
Medium. Tree, Depth-First Search, Binary Tree.

Given the root of a binary tree and an array of TreeNode objects nodes,
return the lowest common ancestor (LCA) of all the nodes in nodes.
All the nodes will exist in the tree, and all values of the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of
n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pi
as a descendant (where we allow a node to be a descendant of itself) for every
valid i". A descendant of a node x is a node y that is on the path from node x
to some leaf node.

Example 1:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 Output: 2
 Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.

Example 2:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 Output: 1
 Explanation: The lowest common ancestor of a single node is the node itself.

Example 3:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 Output: 5
 Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.

Constraints:
 The number of nodes in the tree is in the range [1, 10^4].
 -10^9 <= Node.val <= 10^9
 All Node.val are unique.
 All nodes[i] will exist in the tree.
 All nodes[i] are distinct.
*/

import com.leetcode.utils.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @see _235_LowestCommonAncestorOfABinarySearchTree
 * @see _236_LowestCommonAncestorOfABinaryTree
 * @see _1644_LowestCommonAncestorOfABinaryTreeII
 * @see _1650_LowestCommonAncestorOfABinaryTreeIII
 */
class _1676_LowestCommonAncestorOfABinaryTreeIV {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null || nodes == null || nodes.length == 0) return null;

        final Set<TreeNode> values = new HashSet<>(Arrays.asList(nodes));

        return find(root, values);
    }

    private static TreeNode find(final TreeNode root, final Set<TreeNode> values) {
        if (root == null || values.contains(root)) return root;

        final TreeNode left = find(root.left, values);
        final TreeNode right = find(root.right, values);

        if (left != null && right != null) return root;
        else if (left == null && right == null) return null;
        else if (left != null) return left;
        else /* if (right != null) */ return right;
    }
}
