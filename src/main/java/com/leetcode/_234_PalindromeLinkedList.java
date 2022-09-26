package com.leetcode;

/*
https://leetcode.com/problems/palindrome-linked-list/
Easy. Linked List, Two Pointers, Stack, Recursion.

Given the head of a singly linked list,
return true if it is a palindrome or false otherwise.

Example 1:
 Input: head = [1,2,2,1]
 Output: true

Example 2:
 Input: head = [1,2]
 Output: false

Constraints:
 The number of nodes in the list is in the range [1, 10^5].
 0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
*/

import com.leetcode.utils.ListNode;

class _234_PalindromeLinkedList {

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p1 = head, p2 = reverse(slow.next);
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

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
