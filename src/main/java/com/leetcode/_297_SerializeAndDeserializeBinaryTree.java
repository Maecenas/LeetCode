package com.leetcode;

/*
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
Hard. String, Tree, Depth-First Search, Breadth-First Search, Design, Binary Tree.

Serialization is the process of converting a data structure or object into a
sequence of bits so that it can be stored in a file or memory buffer, or
transmitted across a network connection link to be reconstructed later
in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no
restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string
and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode
serializes a binary tree. You do not necessarily need to follow this format,
so please be creative and come up with different approaches yourself.

Example 1:
 Input: root = [1,2,3,null,null,4,5]
 Output: [1,2,3,null,null,4,5]

Example 2:
 Input: root = []
 Output: []

Constraints:
 The number of nodes in the tree is in the range [0, 10^4].
 -1000 <= Node.val <= 1000

Your Codec object will be instantiated and called as such:

 Codec ser = new Codec();
 Codec deser = new Codec();
 TreeNode ans = deser.deserialize(ser.serialize(root));
*/

import com.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

class _297_SerializeAndDeserializeBinaryTree {

    static class CodecPreOrder {

        private static final String DELIMITER = ",";

        public static String serialize(TreeNode root) {
            final StringJoiner sj = new StringJoiner(DELIMITER);
            serialize(root, sj);
            return sj.toString();
        }

        private static void serialize(TreeNode root, StringJoiner sj) {
            if (root == null) {
                sj.add("");
                return;
            }

            sj.add(String.valueOf(root.val));
            serialize(root.left, sj);
            serialize(root.right, sj);
        }

        public static TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;

            final Deque<String> nodes = new LinkedList<>(List.of(data.split(DELIMITER)));
            return deserialize(nodes);
        }

        private static TreeNode deserialize(Deque<String> nodes) {
            if (nodes.isEmpty()) return null;

            final String first = nodes.removeFirst();
            if (first.isEmpty()) return null;

            final TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);
            return root;
        }
    }

    static class CodecPostOrder {

        private static final String DELIMITER = ",";

        public static String serialize(TreeNode root) {
            final StringJoiner sj = new StringJoiner(DELIMITER);
            serialize(root, sj);
            return sj.toString();
        }

        private static void serialize(TreeNode root, StringJoiner sj) {
            if (root == null) {
                sj.add("");
                return;
            }

            serialize(root.left, sj);
            serialize(root.right, sj);
            sj.add(String.valueOf(root.val));
        }

        public static TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;

            final Deque<String> nodes = new LinkedList<>(List.of(data.split(DELIMITER)));
            return deserialize(nodes);
        }

        private static TreeNode deserialize(Deque<String> nodes) {
            if (nodes.isEmpty()) return null;

            final String last = nodes.removeLast();
            if (last.isEmpty()) return null;

            final TreeNode root = new TreeNode(Integer.parseInt(last));
            root.right = deserialize(nodes);
            root.left = deserialize(nodes);
            return root;
        }
    }

    static class CodecLevelOrder {

        private static final String DELIMITER = ",";
        private static final TreeNode NULL = new TreeNode();

        // Encodes a tree to a single string.
        public static String serialize(TreeNode root) {
            if (root == null) return "";

            final StringJoiner sj = new StringJoiner(DELIMITER);
            final Deque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode curr = queue.poll();
                if (curr == NULL) {
                    sj.add("");
                } else {
                    sj.add(String.valueOf(curr.val));

                    queue.add(curr.left != null ? curr.left : NULL);
                    queue.add(curr.right != null ? curr.right : NULL);
                }
            }

            return sj.toString();
        }

        // Decodes your encoded data to tree.
        public static TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;

            final String[] nodes = data.split(DELIMITER);
            final TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

            final Deque<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            int i = 1;
            while (i < nodes.length && !queue.isEmpty()) {
                final TreeNode parent = queue.poll();
                final String left = nodes[i++];
                if (!left.isEmpty()) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(parent.left);
                } else {
                    parent.left = null;
                }
                if (i >= nodes.length) break;
                final String right = nodes[i++];
                if (!right.isEmpty()) {
                    parent.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(parent.right);
                } else {
                    parent.right = null;
                }
            }
            return root;
        }
    }
}
