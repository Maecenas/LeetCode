package LeetCode;

/*
https://leetcode.com/problems/merge-k-sorted-lists/
Hard. Linked List, Divide and Conquer, Heap.

Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

import LeetCode.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

class _23_MergeKSortedLists {

    // temporary ListNode created only once
    private static final ListNode head = new ListNode(0);

    /**
     * O(nlogk), O(n) / O(k), where:
     *     n is the total number of nodes in all linked lists,
     *     k is the number of linked lists
     * Space complexity explained:
     *     O(n) if we can not modify input, then yield the cost for creating a new linked list O(n),
     *         plus O(k). O(n + k) can be simplified as O(n), as k <= n.
     *     O(k) if we can modify input, then yield the cost for creating a priority queue
     */
    public static ListNode mergeKListsPriorityQueue(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        else if (lists.length == 1) return lists[0];

        // new Comparator
        //final PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
        //    @Override
        //    public int compare(ListNode l1, ListNode l2) {
        //        return l1.val - l2.val;
        //    }
        //});
        // lambda expression
        //final PriorityQueue<ListNode> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        final PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        ListNode curr = head;
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        return head.next;
    }

    /**
     * Recursively (implemented iteratively) merge every two lists, cut k by half
     * O(nlogk), O(1)
     */
    public static ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        else if (lists.length == 1) return lists[0];

        for (int len = lists.length; len > 1; len = (len + 1) >> 1) {
            for (int i = 0; i < len - 1; i += 2) {
                lists[i >> 1] = mergeTwoLists(lists[i], lists[i + 1]);
            }
            // len is odd, then copy the last element
            if ((len & 0x1) != 0x0) {
                lists[len >> 1] = lists[len - 1];
            }
        }
        return lists[0];
    }

    /**
     * {@link _21_MergeTwoSortedLists#mergeTwoListsIteratively}
     */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode curr = head;

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
