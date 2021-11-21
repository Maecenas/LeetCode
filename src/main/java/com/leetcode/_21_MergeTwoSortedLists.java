package com.leetcode;

/*
https://leetcode.com/problems/merge-two-sorted-lists/
Easy. Linked List.

Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/

import com.leetcode.utils.ListNode;

class _21_MergeTwoSortedLists {

    public static ListNode mergeTwoListsRecursively(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursively(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursively(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoListsIteratively(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(-1), curr = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }
        return head.next;
    }
}
