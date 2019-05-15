package LeetCode.utils;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode curr = this;
        while (curr != null) {
            sb.append(curr.val);
            sb.append(", ");
            curr = curr.next;
        }
        sb.setCharAt(sb.length() - 2, ']');
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
