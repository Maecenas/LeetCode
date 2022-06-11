package com.leetcode;

/*
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
Medium. Linked List, Stack, Tree, Depth-First Search, Binary Search Tree, Binary Tree, Doubly-Linked List.

Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the
predecessor and successor pointers in a doubly-linked list.
For a circular doubly linked list, the predecessor of the first element
is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the
left pointer of the tree node should point to its predecessor, and the right
pointer should point to its successor.
You should return the pointer to the smallest element of the linked list.

Example 1:
 Input: root = [4,2,5,1,3]
 Output: [1,2,3,4,5]
 Explanation: The figure below shows the transformed BST.
  The solid line indicates the successor relationship,
  while the dashed line means the predecessor relationship.

Example 2:
 Input: root = [2,1,3]
 Output: [1,2,3]

Constraints:
 The number of nodes in the tree is in the range [0, 2000].
 -1000 <= Node.val <= 1000
 All the values of the tree are unique.
*/

import com.leetcode.utils.TreeNode;

class _426_ConvertBinarySearchTreeToSortedDoublyLinkedList {

    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;

        final TreeNode leftHead, rightHead, leftTail, rightTail;

        if (root.left == null) {
            leftHead = root;
        } else {
            leftHead = treeToDoublyList(root.left);
            leftTail = leftHead.left;
            leftTail.right = root;
            root.left = leftTail;
        }

        if (root.right == null) {
            rightTail = root;
        } else {
            rightHead = treeToDoublyList(root.right);
            rightTail = rightHead.left;
            rightHead.left = root;
            root.right = rightHead;
        }

        leftHead.left = rightTail;
        rightTail.right = leftHead;
        return leftHead;
    }
}
