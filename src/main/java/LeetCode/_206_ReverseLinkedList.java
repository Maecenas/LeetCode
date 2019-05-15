package LeetCode;

/*
https://leetcode.com/problems/reverse-linked-list/
Easy. Linked List.

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Follow up:

A linked list can be reversed either iteratively or recursively.
Could you implement both?
*/

import LeetCode.utils.ListNode;

/**
 * @see _24_SwapNodesInPairs
 * @see _92_ReverseLinkedListII
 * @see _25_ReverseNodesInKGroup
 */
class _206_ReverseLinkedList {

    public static ListNode reverseListRecursively(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode next = head.next;
        head.next = null;
        ListNode newHead = reverseListRecursively(next);
        next.next = head;
        return newHead;
    }

    public static ListNode reverseListIteratively(ListNode head) {
        if (head == null || head.next == null) return head;

        // curr == head, without need for another pointer
        ListNode dummy = new ListNode(0), next;
        while (head != null) {
            next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }
}
