package com.leetcode.utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeNode {

    private static final TreeNode NULL = new TreeNode();

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode fromLevelOrder(Integer[] values) {
        return fromLevelOrder(values, 0);
    }

    private static TreeNode fromLevelOrder(Integer[] values, int i) {
        if (i >= values.length) return null;
        else if (values[i] == null) return NULL;

        return new TreeNode(values[i],
                fromLevelOrder(values, 2 * i + 1),
                fromLevelOrder(values, 2 * i + 2)
        );
    }

    /**
     * Level-order traversal
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        final Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(this);
        TreeNode temp;
        while (!q.isEmpty()) {
            temp = q.poll();
            sb.append(temp != NULL ? temp.val : "null");
            sb.append(", ");
            if (temp != NULL && (temp.left != null || temp.right != null)) {
                q.offer(temp.left != null ? temp.left : NULL);
                q.offer(temp.right != null ? temp.right : NULL);
            }
        }
        sb.setCharAt(sb.length() - 2, ']');
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
