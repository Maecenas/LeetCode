package LeetCode;

/*
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
Medium. Linked List, Depth-first Search.

Given a singly linked list where elements are sorted in ascending order,
convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a
binary tree in which the depth of the two subtrees of every node
never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5],
which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
*/

import LeetCode.utils.ListNode;
import LeetCode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see _108_ConvertSortedArrayToBinarySearchTree
 */
class _109_ConvertSortedListToBinarySearchTree {

    /**
     * O(nlogn), O(logn)
     * Recursion
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        else if (head.next == null) return new TreeNode(head.val);

        ListNode mid = splitAtMiddleElement(head);
        TreeNode node = new TreeNode(mid.val);

        // Recursively construct balanced BSTs using the left and right
        // halves of the original list
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    private static ListNode splitAtMiddleElement(ListNode head) {
        ListNode prev = null, fast = head;

        while (fast != null && fast.next != null) {
            prev = head;
            head = head.next;
            fast = fast.next.next;
        }

        // disconnect the left half from the mid node
        if (prev != null) {
            prev.next = null;
        }

        return head;
    }

    /**
     * O(n), O(n)
     * Recursion + Conversion to Array
     */
    public static TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        else if (head.next == null) return new TreeNode(head.val);

        final List<Integer> nums = convertLinkedListToArray(head);
        return convertListToBST(nums, 0, nums.size() - 1);
    }

    private static List<Integer> convertLinkedListToArray(ListNode head) {
        final List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        return res;
    }

    private static TreeNode convertListToBST(final List<Integer> nums, int lo, int hi) {
        if (lo > hi) return null;
        else if (lo == hi) return new TreeNode(nums.get(lo));

        int mid = lo + ((hi - lo) >> 1);
        TreeNode node = new TreeNode(nums.get(mid));
        node.left = convertListToBST(nums, lo, mid - 1);
        node.right = convertListToBST(nums, mid + 1, hi);
        return node;
    }

    private ListNode head;

    /**
     * O(n), O(logn)
     * Simulated Inorder Traversal
     */
    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) return null;
        else if (head.next == null) return new TreeNode(head.val);

        final int size = getSize(head);
        this.head = head;

        return convertListToBST(0, size - 1);
    }

    private static int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }

    private TreeNode convertListToBST(int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + ((hi - lo) >> 1);

        // simulate inorder traversal, recursively construct the left half
        TreeNode left = convertListToBST(lo, mid - 1);

        // process current node
        // maintain the invariance mentioned in the algorithm
        TreeNode node = new TreeNode(head.val);
        node.left = left;
        head = head.next;

        node.right = convertListToBST(mid + 1, hi);
        return node;
    }
}
