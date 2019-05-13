package LeetCode;

/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Medium. Linked List, Two Pointers.

Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
*/

class _19_RemoveNthNodeFromEndOfList {

    // Given by question
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return head;
        else if (head.next == null && n == 1) return null;

        ListNode dummy = new ListNode(0), slow = dummy, fast = dummy;
        dummy.next = head;
        // first, take (n + 1) moves of fast
        while (fast != null && n >= 0) {
            fast = fast.next;
            n--;
        }
        if (n != -1) throw new IllegalArgumentException("n is larger than the size of Linked List");
        // move slow and fast till the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
