package com.leetcode;

/*
https://leetcode.com/problems/reorder-list/
Medium. Linked List.

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself
may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*/

import com.leetcode.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

class _143_ReorderList {

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        final Deque<ListNode> nodes = new ArrayDeque<>();
        ListNode curr = head.next;
        while (curr != null) {
            nodes.addLast(curr);
            curr = curr.next;
        }
        curr = head;
        while (!nodes.isEmpty()) {
            curr.next = nodes.pollLast();
            curr = curr.next;
            if (!nodes.isEmpty()) {
                curr.next = nodes.pollFirst();
                curr = curr.next;
            }
        }
        curr.next = null;
    }

    public static void reorderList2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        reorderList(head, head.next);
    }

    private static ListNode reorderList(ListNode root, ListNode curr) {
        if (curr == null) return root;

        // passing the initial root to the end
        root = reorderList(root, curr.next);

        if (root == null) return null;

        // We stop reconfiguring in 2 cases.
        // 1. returned new root is same as head: Odd number of list items so we have come to the middle
        // 2. returned new root's next is same as head: Even number of list items
        ListNode temp = null;
        if (root == curr || root.next == curr) {
            curr.next = null;
        } else {
            // make returned root's next to be curr and return root's next as the new root
            temp = root.next;
            root.next = curr;
            curr.next = temp;
        }
        return temp;
    }

    public static void reorderList3(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        // find the middle point
        // slow would stop at middle (odd) or second half (even)
        ListNode prev = head, curr = head, next;
        while (curr != null && curr.next != null) {
            prev = prev.next;
            curr = curr.next.next;
        }

        // reverse the second half
        // curr.next, prev, curr = prev, curr, curr.next
        // side effects: last node all points to middle point
        curr = prev;
        prev = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // merge two lists
        curr = prev;
        prev = head;
        while (curr.next != null) {
            next = prev.next;
            prev.next = curr;
            prev = next;

            next = curr.next;
            curr.next = prev;
            curr = next;
        }
    }

    public static void reorderList4(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        merge(head, reverse(middle(head)));
    }

    private static ListNode middle(ListNode head) {
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
//        prev.next = null;
        return slow;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null, next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    private static void merge(ListNode l1, ListNode l2) {
        ListNode next;
        while (l2.next != null) {
            next = l1.next;
            l1.next = l2;
            l1 = next;

            next = l2.next;
            l2.next = l1;
            l2 = next;
        }
    }
}
