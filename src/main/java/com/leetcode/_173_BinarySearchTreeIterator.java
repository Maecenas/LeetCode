package com.leetcode;

/*
https://leetcode.com/problems/binary-search-tree-iterator/
Medium. Tree, Depth-first Search, Breadth-first Search.

Implement an iterator over a binary search tree (BST).
Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory,
where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be
at least a next smallest number in the BST when next() is called.
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @see _94_BinaryTreeInorderTraversal#inorderTraversalMorris
 */
class _173_BinarySearchTreeIterator {

    /**
     * O(n), O(n)
     * In-order traverse to flatten the BST
     */
    static class BSTIterator {

        List<Integer> nodes;
        int index;

        public BSTIterator(TreeNode root) {
            this.nodes = _94_BinaryTreeInorderTraversal.inorderTraversal(root);
            this.index = 0;
        }

        /**
         * @return the next smallest number
         */
        public int next() throws NoSuchMethodException {
            try {
                return nodes.get(index++);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchMethodException(e.getMessage());
            }
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return index < nodes.size();
        }
    }

    /**
     * O(n), O(h)
     */
    static class BSTIterator2 {

        TreeNode node;
        Deque<TreeNode> stack = new ArrayDeque<>();

        public BSTIterator2(TreeNode root) {
            node = root;
            stack.clear();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            int res = -1;
            while (hasNext()) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    // Add after all left children
                    res = node.val;
                    node = node.right;
                    return res;
                }
            }
            // throw new NoSuchElementException();
            return res;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return node != null || !stack.isEmpty();
        }
    }

    /**
     * Morris Traversal
     * O(n), O(1)
     */
    static class BSTIteratorMorris {

        // next node to visit
        TreeNode node;

        public BSTIteratorMorris(TreeNode root) {
            node = morris(root);
        }

        private static TreeNode morris(TreeNode node) {
            TreeNode left;
            while (node != null) {
                left = node.left;
                if (left != null) {
                    while (left.right != null && left.right != node) {
                        left = left.right;
                    }
                    if (left.right == null) {
                        left.right = node;
                        node = node.left;
                    } else /* if (left.right == root) */ {
                        left.right = null;
                        return node;
                    }
                } else {
                    return node;
                }
            }
            return null;
        }

        public int next() {
            if (!hasNext()) return -1;

            int val = node.val;
            node = morris(node.right);
            return val;
        }

        public boolean hasNext() {
            return node != null;
        }
    }
}
