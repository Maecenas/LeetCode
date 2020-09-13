package LeetCode.utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeNode {

    private static final int NULL = Integer.MIN_VALUE;

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
            sb.append(temp.val != NULL ? temp.val : "null");
            sb.append(", ");
            if (temp.val != NULL && (temp.left != null || temp.right != null)) {
                q.offer(temp.left != null ? temp.left : new TreeNode(NULL));
                q.offer(temp.right != null ? temp.right : new TreeNode(NULL));
            }
        }
        sb.setCharAt(sb.length() - 2, ']');
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
