package LeetCode;

/*
https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
Medium. Math, Tree.

In an infinite binary tree where every node has two children,
the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...),
the labelling is left to right, while in the even numbered rows
(second, fourth, sixth,...), the labelling is right to left.

Given the label of a node in this tree, return the labels in the path
from the root of the tree to the node with that label.

Example 1:

Input: label = 14
Output: [1,3,4,14]

Example 2:

Input: label = 26
Output: [1,2,6,10,26]

Constraints:

1 <= label <= 10^6

Hint 1:
Based on the label of the current node, find what the label must be for the parent of that node.
*/

import java.util.LinkedList;
import java.util.List;

class _1104_PathInZigzagLabelledBinaryTree {

    private static final double LOG_2 = Math.log(2);

    public static List<Integer> pathInZigZagTree(int label) {
        if (label < 1) return new LinkedList<>();

        final List<Integer> res = new LinkedList<>();
        int parent = label;
        res.add(0, parent);

        while (parent != 1) {
            int depth = (int) (Math.log(parent) / LOG_2);
            int offset = ((int) Math.pow(2, depth + 1)) - 1 - parent;
            parent = ((int) (Math.pow(2, depth) + offset)) >> 1;
            res.add(0, parent);
        }

        return res;
    }
}
