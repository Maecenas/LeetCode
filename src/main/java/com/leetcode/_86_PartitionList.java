package com.leetcode;

/*
https://leetcode.com/problems/partition-list/
Medium. Linked List, Two Pointers.

Given the head of a linked list and a value x, partition it such that all
nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the
two partitions.

Example 1:
 Input: head = [1,4,3,2,5,2], x = 3
 Output: [1,2,2,4,3,5]

Example 2:
 Input: head = [2,1], x = 2
 Output: [1,2]

Constraints:
 The number of nodes in the list is in the range [0, 200].
 -100 <= Node.val <= 100
 -200 <= x <= 200
*/

import com.leetcode.utils.ListNode;

class _86_PartitionList {

    public static ListNode partition(ListNode head, int x) {
        if (head == null || x < -200 || x > 200) return head;

        final ListNode dummy1 = new ListNode(-1);
        final ListNode dummy2 = new ListNode(-1);
        ListNode curr = head, next, p1 = dummy1, p2 = dummy2;
        while (curr != null) {
            if (curr.val < x) {
                p1.next = curr;
                p1 = p1.next;
            } else {
                p2.next = curr;
                p2 = p2.next;
            }
            next = curr.next;
            curr.next = null;
            curr = next;
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }
}
