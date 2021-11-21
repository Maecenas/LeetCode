package com.leetcode;

/*
https://leetcode.com/problems/reverse-linked-list-ii/
Medium. Linked List.

Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/

import com.leetcode.utils.ListNode;

/**
 * @see _206_ReverseLinkedList#reverseListIteratively
 * @see _24_SwapNodesInPairs
 * @see _25_ReverseNodesInKGroup
 */
class _92_ReverseLinkedListII {

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m < 1 || n < m) return head;

        ListNode dummy = new ListNode(0), start = dummy;
        dummy.next = head;
        head = dummy;
        int count = 0;
        while (head != null) {
            count++;
            if (count == m) {
                start = head;
            }
            head = head.next;
            if (count == n) {
                reverse(start, head.next);
                break;
            }
        }
        return dummy.next;
    }

    /**
     * Return the tail of reversed linked list bounded at (start, end)
     */
    private static ListNode reverse(final ListNode start, final ListNode end) {
        ListNode prev = start, curr = start.next, next, tail = curr;

        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        start.next = prev;
        tail.next = end;
        return tail;
    }
}
