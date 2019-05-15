package LeetCode;

/*
https://leetcode.com/problems/reverse-nodes-in-k-group/
Hard. Linked List, Two Pointers.

Given a linked list, reverse the nodes of a linked list k at a time
and return its modified list.

k is a positive integer and is less than or equal to the length of
the linked list. If the number of nodes is not a multiple of k
then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

import LeetCode.utils.ListNode;

/**
 * @see _206_ReverseLinkedList#reverseListIteratively
 * @see _24_SwapNodesInPairs
 * @see _92_ReverseLinkedListII
 */
class _25_ReverseNodesInKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;

        ListNode dummy = new ListNode(0);
        ListNode start = dummy;
        dummy.next = head;

        int count = 0;
        while (head != null) {
            count++;
            if (count == k) {
                start = reverse(start, head.next);
                head = start.next;
                count = 0;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    /**
     * Return the tail of reversed linked list bounded at (start, end)
     * {@link _92_ReverseLinkedListII#reverse}
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
