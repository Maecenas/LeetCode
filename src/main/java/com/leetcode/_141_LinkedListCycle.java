package com.leetcode;

/*
https://leetcode.com/problems/linked-list-cycle/
Easy. Linked List, Two Pointers.

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer position
which represents the position (0-indexed) in the linked list where
tail connects to. If pos is -1, then there is no cycle in the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Follow up:

Can you solve it using O(1) (i.e. constant) memory?
*/

import com.leetcode.utils.ListNode;

class _141_LinkedListCycle {

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head, fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) return false;
        } while (slow != fast);
        return true;
    }
}
