package com.leetcode;

/*
https://leetcode.com/problems/add-two-numbers/
Medium. Linked List, Math.

You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

import com.leetcode.utils.ListNode;

class _2_AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;

        final ListNode head = l1;
        int carry = 0;

        while (l1 != null) {
            // Add l2.val and carry to l1.val
            l1.val += (carry + (l2 != null ? l2.val : 0));
            carry = l1.val / 10;
            l1.val %= 10;
            if (l1.next == null && l2 != null) {
                // if l2 is longer than l1, that is, l1.next is null
                l1.next = l2.next;
                l2 = null;
            }
            if (l1.next == null && carry == 1) {
                // Note that l2 is always null
                // Add new node for the carry
                l1.next = new ListNode(carry);
                break;
            }
            l1 = l1.next;
            if (l2 != null) {
                l2 = l2.next;
            }
            // early break if l1 is longer than l1
            if (l2 == null && carry == 0) break;
        }
        return head;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;

        final ListNode head = new ListNode(0);
        ListNode curr = head;
        int carry = 0, residue;
        while (l1 != null || l2 != null) {
            int value1 = l1 == null ? 0 : l1.val;
            int value2 = l2 == null ? 0 : l2.val;
            int sum = value1 + value2 + carry;
            carry = sum / 10;
            residue = sum % 10;
            curr.next = new ListNode(residue);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            curr = curr.next;
        }
        if (carry > 0) curr.next = new ListNode(carry);
        return head.next;
    }
}
