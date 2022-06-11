package com.leetcode;

/*
https://leetcode.com/problems/copy-list-with-random-pointer/
Medium. Hash Table, Linked List.

A linked list of length n is given such that each node contains an additional
random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n
brand new nodes, where each new node has its value set to the value of its
corresponding original node. Both the next and random pointer of the new nodes
should point to new nodes in the copied list such that the pointers in the
original list and copied list represent the same list state.
None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list,
where X.random --> Y, then for the corresponding two nodes x and y
in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes.
Each node is represented as a pair of [val, random_index] where:

 val: an integer representing Node.val
 random_index: the index of the node (range from 0 to n-1) that the random
  pointer points to, or null if it does not point to any node.

Your code will only be given the head of the original linked list.

Example 1:
 Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
 Input: head = [[1,1],[2,1]]
 Output: [[1,1],[2,1]]

Example 3:
 Input: head = [[3,null],[3,0],[3,null]]
 Output: [[3,null],[3,0],[3,null]]

Example 4:
 Input: head = []
 Output: []
 Explanation: The given linked list is empty (null pointer), so return null.

Constraints:
 0 <= n <= 1000
 -10000 <= Node.val <= 10000
 Node.random is null or is pointing to some node in the linked list.
*/

import java.util.HashMap;
import java.util.Map;

class _138_CopyListWithRandomPointer {

    // Definition for a Node.
    private static class Node {
        final int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * O(n), O(n)
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        final Map<Node, Node> cloned = new HashMap<>();

        Node curr = head, dst;
        while (curr != null) {
            dst = cloned.computeIfAbsent(curr, (node) -> new Node(node.val));
            if (curr.next != null) {
                dst.next = cloned.computeIfAbsent(curr.next, (node) -> new Node(node.val));
            }
            if (curr.random != null) {
                dst.random = cloned.computeIfAbsent(curr.random, (node) -> new Node(node.val));
            }
            curr = curr.next;
        }

        return cloned.get(head);
    }

    /**
     * O(n), O(1)
     * Append cloned node after the original node
     */
    public static Node copyRandomList2(Node head) {
        if (head == null) return null;

        Node curr = head, dst;
        // copy the next pointer
        while (curr != null) {
            dst = new Node(curr.val);
            dst.next = curr.next;
            curr.next = dst;
            curr = dst.next;
        }

        curr = head;
        // copy the random pointer
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // unzip two lists
        curr = head;
        dst = head.next;
        Node res = dst;
        while (curr != null) {
            curr.next = dst.next;
            if (curr.next != null) {
                dst.next = dst.next.next;
            }
            curr = curr.next;
            dst = dst.next;
        }
        return res;
    }
}
