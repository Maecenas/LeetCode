package LeetCode;

/*
https://leetcode.com/problems/add-two-numbers-ii/
Medium. Linked List.

You are given two non-empty linked lists representing two non-negative integer
s. The most significant digit comes first and each of their nodes contain a sing
le digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the nu
mber 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists
is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/

import LeetCode.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

class _445_AddTwoNumbersIi {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;

        final Deque<ListNode> stack1 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        final Deque<ListNode> stack2 = new ArrayDeque<>();
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        ListNode head = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            if (!stack1.isEmpty()) carry += stack1.pop().val;
            if (!stack2.isEmpty()) carry += stack2.pop().val;

            ListNode node = new ListNode(carry % 10);
            node.next = head;
            head = node;
            carry /= 10;
        }
        return head;
    }
}
