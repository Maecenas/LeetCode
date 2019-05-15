package LeetCode;

/*
https://leetcode.com/problems/swap-nodes-in-pairs/
Medium. Linked List.

Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
*/

import LeetCode.utils.ListNode;

/**
 * @see _206_ReverseLinkedList#reverseListIteratively
 * @see _92_ReverseLinkedListII
 * @see _25_ReverseNodesInKGroup
 */
class _24_SwapNodesInPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0), next;
        dummy.next = head;
        head = dummy;

        // swap element of head.next and head.next.next
        while (head.next != null && head.next.next != null) {
            next = head.next.next.next;
            head.next.next.next = head.next;
            head.next = head.next.next;
            head.next.next.next = next;

            head = head.next.next;
        }
        return dummy.next;
    }
}
